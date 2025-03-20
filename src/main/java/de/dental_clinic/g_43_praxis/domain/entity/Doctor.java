package de.dental_clinic.g_43_praxis.domain.entity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleDe;
    private String titleEn;
    private String titleRu;
    private String fullName;
    private String biographyDe;
    private String biographyEn;
    private String biographyRu;
    private String specialisationDe;
    private String specialisationEn;
    private String specialisationRu;
    private boolean isActive;
    private String topImage;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    public Doctor() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return isActive == doctor.isActive && Objects.equals(id, doctor.id) && Objects.equals(titleDe, doctor.titleDe) && Objects.equals(titleEn, doctor.titleEn) && Objects.equals(titleRu, doctor.titleRu) && Objects.equals(fullName, doctor.fullName) && Objects.equals(biographyDe, doctor.biographyDe) && Objects.equals(biographyEn, doctor.biographyEn) && Objects.equals(biographyRu, doctor.biographyRu) && Objects.equals(specialisationDe, doctor.specialisationDe) && Objects.equals(specialisationEn, doctor.specialisationEn) && Objects.equals(specialisationRu, doctor.specialisationRu) && Objects.equals(topImage, doctor.topImage) && Objects.equals(images, doctor.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleDe, titleEn, titleRu, fullName, biographyDe, biographyEn, biographyRu, specialisationDe, specialisationEn, specialisationRu, isActive, topImage, images);
    }

    @Override
    public String toString() {
        return String.format(
                "Doctor: id = %d, fullName = %s, title = %s, specialisation = %s, isActive = %b, topImage = '%s' ",
                id,
                fullName,
                titleEn,
                specialisationEn,
                isActive,
                topImage
        );
    }

}
