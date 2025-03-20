package de.dental_clinic.g_43_praxis.domain.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleDe;
    private String titleEn;
    private String titleRu;

    @Column(columnDefinition = "TEXT")
    private String descriptionDe;

    @Column(columnDefinition = "TEXT")
    private String descriptionEn;

    @Column(columnDefinition = "TEXT")
    private String descriptionRu;

    private String topImage;

    private boolean isActive;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    public Service() {
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
        Service service = (Service) o;
        return isActive == service.isActive &&
                Objects.equals(id, service.id) &&
                Objects.equals(titleDe, service.titleDe) &&
                Objects.equals(titleEn, service.titleEn) &&
                Objects.equals(titleRu, service.titleRu) &&
                Objects.equals(descriptionDe, service.descriptionDe) &&
                Objects.equals(descriptionEn, service.descriptionEn) &&
                Objects.equals(descriptionRu, service.descriptionRu) &&
                Objects.equals(topImage, service.topImage) &&
                Objects.equals(images, service.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleDe, titleEn, titleRu, descriptionDe, descriptionEn, descriptionRu, topImage, isActive, images);
    }

    // toString
    @Override
    public String toString() {
        return String.format(
                "Service: id = %d, title = '%s', description = '%s', isActive = %b, topImage = '%s'}",
                id, titleEn, descriptionEn, isActive, topImage
        );
    }
}
