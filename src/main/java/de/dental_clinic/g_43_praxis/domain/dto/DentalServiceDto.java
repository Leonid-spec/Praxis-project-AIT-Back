package de.dental_clinic.g_43_praxis.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "titleDe",
        "titleEn",
        "titleRu",
        "descriptionDe",
        "descriptionEn",
        "descriptionRu",
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
@Schema(description = "DTO representing a dental service with multilingual titles, descriptions, images, and status")
public class DentalServiceDto {

    @Schema(description = "Unique identifier of the dental service", example = "1")
    private Long id;

    @Schema(description = "Title of the dental service in German", example = "Zahnpflege")
    @JsonProperty("titleDe")
    @NotNull(message = "Title in German is required")
    @Size(min = 3, message = "Title in German must be at least 3 characters long")
    private String titleDe;

    @Schema(description = "Title of the dental service in English", example = "Dental Care")
    @JsonProperty("titleEn")
    @NotNull(message = "Title in English is required")
    @Size(min = 3, message = "Title in English must be at least 3 characters long")
    private String titleEn;

    @Schema(description = "Title of the dental service in Russian", example = "Стоматологическая помощь")
    @JsonProperty("titleRu")
    @NotNull(message = "Title in Russian is required")
    @Size(min = 3, message = "Title in Russian must be at least 3 characters long")
    private String titleRu;

    @Schema(description = "Description of the dental service in German", example = "Komplette Zahnpflege für die Familie")
    @JsonProperty("descriptionDe")
    @NotNull(message = "Description in German is required")
    @Size(min = 10, message = "Description in German must be at least 10 characters long")
    private String descriptionDe;

    @Schema(description = "Description of the dental service in English", example = "Comprehensive dental care for the whole family")
    @JsonProperty("descriptionEn")
    @NotNull(message = "Description in English is required")
    @Size(min = 10, message = "Description in English must be at least 10 characters long")
    private String descriptionEn;

    @Schema(description = "Description of the dental service in Russian", example = "Комплексный уход за зубами для всей семьи")
    @JsonProperty("descriptionRu")
    @NotNull(message = "Description in Russian is required")
    @Size(min = 10, message = "Description in Russian must be at least 10 characters long")
    private String descriptionRu;

    @Schema(description = "URL to the top image representing the dental service", example = "/images/dental/top-image.jpg")
    @JsonProperty("topImage")
    @NotNull(message = "Top image is required")
    private String topImage;

//    @Schema(description = "Status indicating if the service is active", example = "true")
    @JsonProperty("isActive")
//    @NotNull(message = "Is Active is required")
    private Boolean isActive;

    @Schema(description = "List of associated images")
    @JsonProperty("images")
    private List<ImageDto> images;
}