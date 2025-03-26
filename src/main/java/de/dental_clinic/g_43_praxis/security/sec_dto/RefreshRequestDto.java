package de.dental_clinic.g_43_praxis.security.sec_dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RefreshRequestDto {
    private String refreshToken;
}