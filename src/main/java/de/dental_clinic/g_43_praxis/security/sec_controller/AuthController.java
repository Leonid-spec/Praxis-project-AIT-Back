package de.dental_clinic.g_43_praxis.security.sec_controller;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.AuthException;
import de.dental_clinic.g_43_praxis.security.sec_dto.RefreshRequestDto;
import de.dental_clinic.g_43_praxis.security.sec_dto.TokenResponseDto;
import de.dental_clinic.g_43_praxis.security.sec_service.AuthService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ApiResponse(responseCode = "401", description = "Authentication error")
    public ResponseEntity<String> handleAuthException(AuthException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody(required = false) AdminDto adminDto) {
        try {
            TokenResponseDto tokenResponse = authService.login(adminDto);
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refresh(@RequestBody RefreshRequestDto refreshRequest) {
        TokenResponseDto tokenResponse = authService.getNewAccessToken(refreshRequest.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    }
}
