package de.dental_clinic.g_43_praxis.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.constraints.NotBlank;
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
public class DentalServiceDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("titleDe")
    @NotBlank(message = "Field titleEn cannot be null or empty.")
    private String titleDe;

    @JsonProperty("titleEn")
    @NotBlank(message = "Field titleDe cannot be null or empty.")
    private String titleEn;

    @JsonProperty("titleRu")
    @NotBlank(message = "Field titleRu cannot be null or empty.")
    private String titleRu;

    @JsonProperty("descriptionDe")
    @NotBlank(message = "Field descriptionDe cannot be null or empty.")
    private String descriptionDe;

    @JsonProperty("descriptionEn")
    @NotBlank(message = "Field descriptionEn cannot be null or empty.")
    private String descriptionEn;

    @JsonProperty("descriptionRu")
    @NotBlank(message = "Field descriptionRu cannot be null or empty.")
    private String descriptionRu;

    @JsonProperty("topImage")
    @NotBlank(message = "Field topImage cannot be null or empty.")
    private String topImage;

    @JsonProperty("isActive")
    private Boolean isActive = true;

    @JsonProperty("images")
    private List<ImageDto> images;
}