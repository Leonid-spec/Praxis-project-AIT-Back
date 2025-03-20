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
    @JoinColumn(name = "doctor_id", nullable = true)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = true)
    private Service service;

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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(path, image.path) && Objects.equals(doctor, image.doctor) && Objects.equals(service, image.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, doctor, service);
    }

    @Override
    public String toString() {
        return String.format("Image: id = %d, path = '%s', doctorId = %d, serviceId = %d",
                id, path, doctor != null ? doctor.getId() : null, service != null ? service.getId() : null);
    }
}
