package de.dental_clinic.g_43_praxis.domain.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({
        "id",
        "title_de",
        "title_en",
        "title_ru",
        "full_name",
        "description_de",
        "description_en",
        "description_ru",
        "specialisation_de",
        "specialisation_en",
        "specialisation_ru",
        "top_image",
        "is_active",
        "images"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class DoctorDto {
    private Long id;
    private String fullName;

    private String titleDe;
    private String titleEn;
    private String titleRu;

    private String biographyDe;
    private String biographyEn;
    private String biographyRu;

    private String specialisationDe;
    private String specialisationEn;
    private String specialisationRu;

    private String topImage;
    private boolean isActive;

    private List<ImageDto> images;
}
