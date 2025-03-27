package de.dental_clinic.g_43_praxis.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jshell.Snippet;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonPropertyOrder({
        "id",
        "dental_service_id",
        "first_name",
        "last_name",
        "phone1",
        "phone2",
        "email",
        "available_time",
        "comment",
        "language",
        "status"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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
    @JsonProperty("is_active")
    private Boolean isActive;
}
