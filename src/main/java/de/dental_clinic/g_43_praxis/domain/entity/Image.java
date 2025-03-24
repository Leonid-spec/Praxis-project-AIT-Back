package de.dental_clinic.g_43_praxis.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image")
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "path", nullable = false, columnDefinition = "TEXT")
    private String path;

    @ManyToOne
    @JoinColumn(name = "dental_service_id", nullable = true)
    private DentalService dentalService;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = true)
    private Doctor doctor;

    public Image() {
    }
}
