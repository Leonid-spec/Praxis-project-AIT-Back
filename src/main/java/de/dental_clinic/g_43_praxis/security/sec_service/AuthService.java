package de.dental_clinic.g_43_praxis.security.sec_service;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.domain.entity.Admin;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.AuthException;
import de.dental_clinic.g_43_praxis.security.sec_dto.TokenResponseDto;
import de.dental_clinic.g_43_praxis.service.interfaces.AdminService;
import de.dental_clinic.g_43_praxis.service.mapping.AdminMappingService;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final AdminService adminService;
    private final TokenService tokenService;
    private final Map<String, String> refreshStorage;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AdminMappingService adminMappingService;

    public AuthService(AdminService adminService, TokenService tokenService, BCryptPasswordEncoder passwordEncoder, AdminMappingService adminMappingService) {
        this.adminService = adminService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.adminMappingService = adminMappingService;
        this.refreshStorage = new HashMap<>();
    }

    public TokenResponseDto login(AdminDto inboundUser) throws AuthException {
        Admin admin = adminMappingService.mapDtoToEntity(inboundUser);

        String username = admin.getUsername();
        Optional<AdminDto> foundUser = adminService.findByLogin(username);

        if (foundUser.isEmpty()) {
            throw new AuthException("Admin not found");
        }

        if (passwordEncoder.matches(inboundUser.getPassword(), foundUser.get().getPassword())) {
            String accessToken = tokenService.generateAccessToken(foundUser);
            String refreshToken = tokenService.generateRefreshToken(foundUser);
            refreshStorage.put(username, refreshToken);
            return new TokenResponseDto(accessToken, refreshToken);
        } else {
            throw new AuthException("Password is incorrect");
        }
    }

    public TokenResponseDto getNewAccessToken(String inboundRefreshToken) {
        Claims refreshClaims = tokenService.getRefreshClaims(inboundRefreshToken);
        String username = refreshClaims.getSubject();
        String foundRefreshToken = refreshStorage.get(username);

        if (foundRefreshToken != null && foundRefreshToken.equals(inboundRefreshToken)) {
            Optional<AdminDto> foundUser = adminService.findByLogin(username);
            if (foundUser.isPresent()) {
                String accessToken = tokenService.generateAccessToken(Optional.of(foundUser.get()));
                return new TokenResponseDto(accessToken);
            } else {
                throw new AuthException("Admin not found");
            }
        } else {
            throw new AuthException("Invalid refresh token");
        }
    }
}
