package de.dental_clinic.g_43_praxis.security.sec_controller;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.security.sec_dto.RefreshRequestDto;
import de.dental_clinic.g_43_praxis.security.sec_dto.TokenResponseDto;
import de.dental_clinic.g_43_praxis.security.sec_service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Authentication", description = "Controller responsible for user authentication")
public class AuthController {

    private final AuthService authService;

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ApiResponse(responseCode = "401", description = "Authentication error")
    public ResponseEntity<String> handleAuthException(AuthException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @Operation(summary = "Admin authentication", description = "Validates login credentials and returns JWT tokens.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful login. Returns access and refresh tokens."),
            @ApiResponse(responseCode = "401", description = "Invalid login or password.")
    })
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody AdminDto adminDto) {
        TokenResponseDto tokenResponse = authService.login(adminDto);
        return ResponseEntity.ok(tokenResponse);
    }

    @Operation(summary = "Refresh access token", description = "Generates a new access token using a valid refresh token.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "New access token successfully created."),
            @ApiResponse(responseCode = "401", description = "Invalid or expired refresh token.")
    })
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refresh(@RequestBody RefreshRequestDto refreshRequest) {
        TokenResponseDto tokenResponse = authService.getNewAccessToken(refreshRequest.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    }
}
