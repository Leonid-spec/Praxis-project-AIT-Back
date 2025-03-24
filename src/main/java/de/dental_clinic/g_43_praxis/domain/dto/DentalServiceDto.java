package de.dental_clinic.g_43_praxis.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

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

    //    @JsonProperty("isActive")
    @JsonProperty("is_active")
    @NotNull(message = "Is Active is required")
    private Boolean isActive;

    private List<ImageDto> images;

    public DentalServiceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleDe() {
        return titleDe;
    }

    public void setTitleDe(String titleDe) {
        this.titleDe = titleDe;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getDescriptionDe() {
        return descriptionDe;
    }

    public void setDescriptionDe(String descriptionDe) {
        this.descriptionDe = descriptionDe;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DentalServiceDto that = (DentalServiceDto) o;
        return isActive == that.isActive && Objects.equals(id, that.id) && Objects.equals(titleDe, that.titleDe) && Objects.equals(titleEn, that.titleEn) && Objects.equals(titleRu, that.titleRu) && Objects.equals(descriptionDe, that.descriptionDe) && Objects.equals(descriptionEn, that.descriptionEn) && Objects.equals(descriptionRu, that.descriptionRu) && Objects.equals(topImage, that.topImage) && Objects.equals(images, that.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleDe, titleEn, titleRu, descriptionDe, descriptionEn, descriptionRu, topImage, isActive, images);
    }

    @Override
    public String toString() {
        return String.format("DentalServiceDto: id=%d, titleEn='%s', descriptionEn='%s', topImage='%s', isActive=%b, images=%s",
                id, titleEn, descriptionEn, topImage, isActive, images);
    }
}
