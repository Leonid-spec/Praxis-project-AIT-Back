package de.dental_clinic.g_43_praxis.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "site_settings")
public class SiteSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
