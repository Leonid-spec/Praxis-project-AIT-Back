package de.dental_clinic.g_43_praxis.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dental_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DentalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title_de")
    private String titleDe;

    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "title_ru")
    private String titleRu;

    @Column(name = "description_de", columnDefinition = "TEXT")
    private String descriptionDe;

    @Column(name = "description_en", columnDefinition = "TEXT")
    private String descriptionEn;

    @Column(name = "description_ru", columnDefinition = "TEXT")
    private String descriptionRu;

    @Column(name = "top_image")
    private String topImage;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "dentalService", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Image> images;

}
