package de.dental_clinic.g_43_praxis.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "title_de",
        "title_en",
        "title_ru",
        "description_de",
        "description_en",
        "description_ru",
        "top_image",
        "images",
        "is_active"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DentalServiceDto {

    private Long id;

    @JsonProperty("title_de")
    @NotNull(message = "Title in German is required")
    @Size(min = 3, message = "Title in German must be at least 3 characters long")
    private String titleDe;

    @JsonProperty("title_en")
    @NotNull(message = "Title in English is required")
    @Size(min = 3, message = "Title in English must be at least 3 characters long")
    private String titleEn;

    @JsonProperty("title_ru")
    @NotNull(message = "Title in Russian is required")
    @Size(min = 3, message = "Title in Russian must be at least 3 characters long")
    private String titleRu;

    @JsonProperty("description_de")
    @NotNull(message = "Description in German is required")
    @Size(min = 10, message = "Description in German must be at least 10 characters long")
    private String descriptionDe;

    @JsonProperty("description_en")
    @NotNull(message = "Description in English is required")
    @Size(min = 10, message = "Description in English must be at least 10 characters long")
    private String descriptionEn;

    @JsonProperty("description_ru")
    @NotNull(message = "Description in Russian is required")
    @Size(min = 10, message = "Description in Russian must be at least 10 characters long")
    private String descriptionRu;

    @JsonProperty("top_image")
    @NotNull(message = "Top image is required")
    private String topImage;

    @JsonProperty("is_active")
    @NotNull(message = "Is Active is required")
    private Boolean isActive;

    private List<ImageDto> images;
}
