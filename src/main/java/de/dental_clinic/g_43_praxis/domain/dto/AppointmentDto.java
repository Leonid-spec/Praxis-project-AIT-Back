package de.dental_clinic.g_43_praxis.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

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
    private Boolean isNew;
//    @JsonProperty("isNew")
//    private String status;
//    private Boolean isActive;
}
