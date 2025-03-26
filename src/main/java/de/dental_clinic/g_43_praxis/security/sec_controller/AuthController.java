package de.dental_clinic.g_43_praxis.security.sec_controller;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.security.sec_dto.RefreshRequestDto;
import de.dental_clinic.g_43_praxis.security.sec_dto.TokenResponseDto;
import de.dental_clinic.g_43_praxis.security.sec_service.AuthService;
import de.dental_clinic.g_43_praxis.service.interfaces.AdminService;
import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;
    private AdminService adminService;

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handleAuthException(AuthException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody AdminDto adminDto) {
        TokenResponseDto tokenResponse = authService.login(adminDto);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refresh(@RequestBody RefreshRequestDto refreshRequest) {
        TokenResponseDto tokenResponse = authService.getNewAccessToken(refreshRequest.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    }

//    @PostMapping("/admin")
//    public ResponseEntity<TokenResponseDto> createAdmin(@RequestBody AdminDto adminDto) {
//        if (authService.existsByUsername(adminDto.getUsername())) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body(new TokenResponseDto("Admin with this username already exists"));
//        }
//
//        TokenResponseDto tokenResponse = authService.createAdmin(adminDto);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
//    }
    @PostMapping("/admin")
    public ResponseEntity<String> createAdmin(@RequestBody AdminDto adminDto) {
        if (adminService.findByLogin(adminDto.getLogin()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Admin with this username already exists");
        }

        adminService.createAdmin(adminDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Admin created successfully");
    }
}