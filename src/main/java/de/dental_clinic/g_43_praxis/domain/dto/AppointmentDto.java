package de.dental_clinic.g_43_praxis.domain.dto;

import java.util.Objects;

public class AppointmentDto {

    private Long id;
    private Long dentalServiceId;
    private String firstName;
    private String lastName;
    private String phone1;
    private String phone2;
    private String email;
    private String availableTime;
    private String comment;
    private String language;
    private String status;

    public AppointmentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDentalServiceId() {
        return dentalServiceId;
    }

    public void setDentalServiceId(Long dentalServiceId) {
        this.dentalServiceId = dentalServiceId;
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

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentDto that = (AppointmentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dentalServiceId, that.dentalServiceId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(phone1, that.phone1) &&
                Objects.equals(phone2, that.phone2) &&
                Objects.equals(email, that.email) &&
                Objects.equals(availableTime, that.availableTime) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(language, that.language) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dentalServiceId, firstName, lastName, phone1, phone2, email, availableTime, comment, language, status);
    }

    @Override
    public String toString() {
        return String.format("AppointmentDto: id = %d, dentalServiceId = %d, firstName = '%s', lastName = '%s', phone1 = '%s', email = '%s', availableTime = '%s', language = '%s', status = '%s'",
                id, dentalServiceId, firstName, lastName, phone1, email, availableTime, language, status);
    }
}
