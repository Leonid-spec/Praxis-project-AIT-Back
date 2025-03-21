package de.dental_clinic.g_43_praxis.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class DentalServiceDto {
    private Long id;

    private String titleDe;
    private String titleEn;
    private String titleRu;

    private String descriptionDe;
    private String descriptionEn;
    private String descriptionRu;

//    @JsonProperty("top_image")
    private String topImage;
    private boolean isActive;

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
