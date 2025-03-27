package de.dental_clinic.g_43_praxis.domain.dto;

import java.util.List;
import java.util.Objects;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getBiographyDe() {
        return biographyDe;
    }

    public void setBiographyDe(String biographyDe) {
        this.biographyDe = biographyDe;
    }

    public String getBiographyEn() {
        return biographyEn;
    }

    public void setBiographyEn(String biographyEn) {
        this.biographyEn = biographyEn;
    }

    public String getBiographyRu() {
        return biographyRu;
    }

    public void setBiographyRu(String biographyRu) {
        this.biographyRu = biographyRu;
    }

    public String getSpecialisationDe() {
        return specialisationDe;
    }

    public void setSpecialisationDe(String specialisationDe) {
        this.specialisationDe = specialisationDe;
    }

    public String getSpecialisationEn() {
        return specialisationEn;
    }

    public void setSpecialisationEn(String specialisationEn) {
        this.specialisationEn = specialisationEn;
    }

    public String getSpecialisationRu() {
        return specialisationRu;
    }

    public void setSpecialisationRu(String specialisationRu) {
        this.specialisationRu = specialisationRu;
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
        DoctorDto doctorDto = (DoctorDto) o;
        return isActive == doctorDto.isActive && Objects.equals(id, doctorDto.id) && Objects.equals(fullName, doctorDto.fullName) && Objects.equals(titleDe, doctorDto.titleDe) && Objects.equals(titleEn, doctorDto.titleEn) && Objects.equals(titleRu, doctorDto.titleRu) && Objects.equals(biographyDe, doctorDto.biographyDe) && Objects.equals(biographyEn, doctorDto.biographyEn) && Objects.equals(biographyRu, doctorDto.biographyRu) && Objects.equals(specialisationDe, doctorDto.specialisationDe) && Objects.equals(specialisationEn, doctorDto.specialisationEn) && Objects.equals(specialisationRu, doctorDto.specialisationRu) && Objects.equals(topImage, doctorDto.topImage) && Objects.equals(images, doctorDto.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, titleDe, titleEn, titleRu, biographyDe, biographyEn, biographyRu, specialisationDe, specialisationEn, specialisationRu, topImage, isActive, images);
    }

    @Override
    public String toString() {
        return String.format(
                "Doctor: id = %d, fullName = %s, title = %s, specialisation = %s, isActive = %b",
                id,
                fullName,
                titleEn,
                specialisationEn,
                isActive
        );
    }
}
