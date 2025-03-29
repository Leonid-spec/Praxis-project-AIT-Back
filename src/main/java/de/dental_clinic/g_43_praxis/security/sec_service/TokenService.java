package de.dental_clinic.g_43_praxis.security.sec_service;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.domain.entity.Admin;
import de.dental_clinic.g_43_praxis.domain.entity.Role;
import de.dental_clinic.g_43_praxis.repository.RoleRepository;
import de.dental_clinic.g_43_praxis.security.AuthInfo;
import de.dental_clinic.g_43_praxis.service.mapping.AdminMappingService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class TokenService {
    private final SecretKey accessKey;
    private final SecretKey refreshKey;
    private final RoleRepository roleRepository;
    private final AdminMappingService adminMappingService;

    @Autowired
    public TokenService(
            @Value("${key.access}") String accessPhrase,
            @Value("${key.refresh}") String refreshPhrase,
            RoleRepository roleRepository,
            AdminMappingService adminMappingService
    ) {
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessPhrase));
        this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshPhrase));
        this.roleRepository = roleRepository;
        this.adminMappingService = adminMappingService;
    }

    public String generateAccessToken(Optional<AdminDto> adminDtoOptional) {
        AdminDto adminDto = adminDtoOptional.orElseThrow(() ->
                new IllegalArgumentException("AdminDto cannot be null")
        );
        Admin admin = adminMappingService.mapDtoToEntity(adminDto);
        LocalDateTime currentDate = LocalDateTime.now();
        Instant expiration = currentDate.plusDays(1).atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(expiration);

        return Jwts.builder()
                .subject(admin.getLogin())
                .expiration(expirationDate)
                .signWith(accessKey)
                .claim("roles", admin.getAuthorities())
                .claim("name", admin.getUsername())
                .compact();
    }

    public String generateRefreshToken(Optional<AdminDto> adminDtoOptional) {
        AdminDto adminDto = adminDtoOptional.orElseThrow(() ->
                new IllegalArgumentException("AdminDto cannot be null")
        );
        Admin admin = adminMappingService.mapDtoToEntity(adminDto);
        LocalDateTime currentDate = LocalDateTime.now();
        Instant expiration = currentDate.plusDays(7).atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(expiration);

        return Jwts.builder()
                .subject(admin.getUsername())
                .expiration(expirationDate)
                .signWith(refreshKey)
                .compact();
    }

    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken, accessKey);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, refreshKey);
    }

    private boolean validateToken(String token, SecretKey key) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getAccessClaims(String accessToken) {
        return getClaims(accessToken, accessKey);
    }

    public Claims getRefreshClaims(String refreshToken) {
        return getClaims(refreshToken, refreshKey);
    }

    private Claims getClaims(String token, SecretKey key) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public AuthInfo mapClaimsToAuthInfo(Claims claims) {
        String username = claims.getSubject();
        String authoritiesStr = claims.get("authorities", String.class);
        Set<Role> roles = new HashSet<>();

        if (authoritiesStr != null && !authoritiesStr.isEmpty()) {
            for (String roleName : authoritiesStr.split(",")) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
                roles.add(role);
            }
        }

        return new AuthInfo(username, roles);
    }
}
