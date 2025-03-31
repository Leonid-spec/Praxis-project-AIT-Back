package de.dental_clinic.g_43_praxis.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({
        "id",
        "titleDe",
        "titleEn",
        "titleRu",
        "fullName",
        "biographyDe",
        "biographyEn",
        "biographyRu",
        "specialisationDe",
        "specialisationEn",
        "specialisationRu",
        "topImage",
        "isActive",
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
    @JsonProperty("id")
    private Long id;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("titleDe")
    private String titleDe;

    @JsonProperty("titleEn")
    private String titleEn;

    @JsonProperty("titleRu")
    private String titleRu;

    @JsonProperty("biographyDe")
    private String biographyDe;

    @JsonProperty("biographyEn")
    private String biographyEn;

    @JsonProperty("biographyRu")
    private String biographyRu;

    @JsonProperty("specialisationDe")
    private String specialisationDe;

    @JsonProperty("specialisationEn")
    private String specialisationEn;

    @JsonProperty("specialisationRu")
    private String specialisationRu;

    @JsonProperty("topImage")
    private String topImage;

    @JsonProperty("isActive")
    private Boolean isActive = true;

    @JsonProperty("images")
    private List<ImageDto> images;
}
