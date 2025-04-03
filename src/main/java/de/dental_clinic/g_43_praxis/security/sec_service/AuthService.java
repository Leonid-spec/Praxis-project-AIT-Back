package de.dental_clinic.g_43_praxis.security.sec_service;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.AuthException;
import de.dental_clinic.g_43_praxis.security.sec_dto.TokenResponseDto;
import de.dental_clinic.g_43_praxis.service.interfaces.AdminService;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final AdminService adminService;
    private final TokenService tokenService;
    private final Map<String, String> refreshStorage;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(AdminService adminService, TokenService tokenService, BCryptPasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.refreshStorage = new HashMap<>();
    }

    public TokenResponseDto login(AdminDto inboundUser) throws AuthException {
        adminService.validateAdminDto(inboundUser);

        AdminDto foundUser = adminService.findByLogin(inboundUser.getLogin())
                .orElseThrow(() ->new AuthException("Invalid login or password."));

        if (!passwordEncoder.matches(inboundUser.getPassword(), foundUser.getPassword())) {
            throw new AuthException("Invalid login or password.");
        }

        String accessToken = tokenService.generateAccessToken(Optional.of(foundUser));
        String refreshToken = tokenService.generateRefreshToken(Optional.of(foundUser));
        refreshStorage.put(foundUser.getLogin(), refreshToken);

        return new TokenResponseDto(foundUser.getLogin(), accessToken, refreshToken);
    }

    public TokenResponseDto getNewAccessToken(String inboundRefreshToken) {
        Claims refreshClaims = tokenService.getRefreshClaims(inboundRefreshToken);
        String username = refreshClaims.getSubject();
        String foundRefreshToken = refreshStorage.get(username);

        if (foundRefreshToken != null && foundRefreshToken.equals(inboundRefreshToken)) {
            Optional<AdminDto> foundUser = adminService.findByLogin(username);
            if (foundUser.isPresent()) {
                String accessToken = tokenService.generateAccessToken(Optional.of(foundUser.get()));
                return new TokenResponseDto(username, accessToken);
            } else {
                throw new AuthException("Admin not found");
            }
        } else {
            throw new AuthException("Invalid refresh token");
        }
    }
}
