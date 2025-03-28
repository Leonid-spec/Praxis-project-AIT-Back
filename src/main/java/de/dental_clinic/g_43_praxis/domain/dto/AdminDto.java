package de.dental_clinic.g_43_praxis.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonPropertyOrder({
        "id",
        "login",
        "password"
})
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
public class AdminDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("login")
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
