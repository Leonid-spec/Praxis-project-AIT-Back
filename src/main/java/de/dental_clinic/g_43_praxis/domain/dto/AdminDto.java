package de.dental_clinic.g_43_praxis.domain.dto;

import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AdminDto {
    private String login;
    private String password;
}
