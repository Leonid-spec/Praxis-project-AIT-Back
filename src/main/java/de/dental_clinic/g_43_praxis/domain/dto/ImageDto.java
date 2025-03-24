package de.dental_clinic.g_43_praxis.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ImageDto {

    private Long id;
    private String path;
}