package de.dental_clinic.g_43_praxis.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonPropertyOrder({
        "id",
        "dentalServiceSectionId",
        "firstName",
        "lastName",
        "phone1",
        "phone2",
        "email",
        "availableTime",
        "comment",
        "language",
        "isNew"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AppointmentDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("dentalServiceSectionId")
    private Long dentalServiceId;

    @NotNull(message = "First name cannot be null")
    @Size(min = 1, message = "First name cannot be empty")
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("phone1")
    private String phone1;

    @JsonProperty("phone2")
    private String phone2;

    @JsonProperty("email")
    private String email;

    @JsonProperty("availableTime")
    private String availableTime;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("language")
    private String language;

    @JsonProperty("isNew")
    private Boolean isNew = true;
//    @JsonProperty("isNew")
//    private String status;
//    private Boolean isActive;
}
