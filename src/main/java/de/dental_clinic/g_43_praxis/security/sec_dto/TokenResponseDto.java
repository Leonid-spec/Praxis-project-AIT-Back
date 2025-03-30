package de.dental_clinic.g_43_praxis.security.sec_dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonPropertyOrder({
        "login",
        "token"
})
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TokenResponseDto {

    @JsonProperty("login")
    private String login;

    @JsonProperty("token")
    private String accessToken;

    @JsonIgnore
    private String refreshToken;

    public TokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
    public TokenResponseDto(String login, String accessToken, String refreshToken) {
        this.login = login;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenResponseDto(String login, String accessToken) {
        this.login = login;
        this.accessToken = accessToken;
    }
}
