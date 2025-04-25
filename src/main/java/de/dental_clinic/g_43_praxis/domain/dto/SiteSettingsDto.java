package de.dental_clinic.g_43_praxis.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteSettingsDto {

    private String clinicName;
    private String street;
    private String city;
    private String zipCode;
    private String gps;
    private String phone;
    private String email;

    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    private String runningText;
}
