package de.dental_clinic.g_43_praxis.domain.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "dental_service_id", nullable = true)
    private DentalService dentalService;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = true)
    private Doctor doctor;

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DentalService getDentalService() {
        return dentalService;
    }

    public void setDentalService(DentalService service) {
        this.dentalService = service;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(path, image.path) && Objects.equals(dentalService, image.dentalService) && Objects.equals(doctor, image.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, dentalService, doctor);
    }

    @Override
    public String toString() {
        return String.format("Image: id = %d, path = '%s'", id, path);
    }
}
