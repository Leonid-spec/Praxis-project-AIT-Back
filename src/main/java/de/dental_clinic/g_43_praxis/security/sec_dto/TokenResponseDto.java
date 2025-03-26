package de.dental_clinic.g_43_praxis.security.sec_dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;

    public TokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
