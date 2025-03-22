package de.dental_clinic.g_43_praxis.domain.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dental_service_id", nullable = false)
    private DentalService service;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone1", nullable = false)
    private String phone1;

    @Column(name = "phone2")
    private String phone2;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "available_time", columnDefinition = "TEXT")
    private String availableTime;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "language")
    private String language;

    @Column(name = "status")
    private String status;

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DentalService getService() {
        return service;
    }

    public void setService(DentalService service) {
        this.service = service;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone) {
        this.phone1 = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id) && Objects.equals(service, that.service) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phone1, that.phone1) && Objects.equals(phone2, that.phone2) && Objects.equals(email, that.email) && Objects.equals(availableTime, that.availableTime) && Objects.equals(comment, that.comment) && Objects.equals(language, that.language) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, service, firstName, lastName, phone1, phone2, email, availableTime, comment, language, status);
    }

    @Override
    public String toString() {
        return String.format(
                "Appointment: id = %d, serviceId = %d, firstName = %s, lastName = %s, phone = %s, email = %s, availableTime = %s, language = '%s', status = '%s'",
                id,
                service != null ? service.getId() : null,
                firstName,
                lastName,
                phone1,
                email,
                availableTime,
                language,
                status
        );
    }
}
