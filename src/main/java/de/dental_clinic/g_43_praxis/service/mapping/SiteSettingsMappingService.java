package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.SiteSettingsDto;
import de.dental_clinic.g_43_praxis.domain.entity.SiteSettings;
import org.springframework.stereotype.Service;

@Service
public class SiteSettingsMappingService {

    public SiteSettingsDto toDto(SiteSettings entity) {
        SiteSettingsDto dto = new SiteSettingsDto();

        dto.setClinicName(entity.getClinicName());
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setZipCode(entity.getZipCode());
        dto.setGps(entity.getGps());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());

        dto.setMonday(entity.getMonday());
        dto.setTuesday(entity.getTuesday());
        dto.setWednesday(entity.getWednesday());
        dto.setThursday(entity.getThursday());
        dto.setFriday(entity.getFriday());

        dto.setRunningText(entity.getRunningText());

        return dto;
    }

    public void updateEntityFromDto(SiteSettingsDto dto, SiteSettings entity) {
        entity.setClinicName(dto.getClinicName());
        entity.setStreet(dto.getStreet());
        entity.setCity(dto.getCity());
        entity.setZipCode(dto.getZipCode());
        entity.setGps(dto.getGps());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());

        entity.setMonday(dto.getMonday());
        entity.setTuesday(dto.getTuesday());
        entity.setWednesday(dto.getWednesday());
        entity.setThursday(dto.getThursday());
        entity.setFriday(dto.getFriday());

        entity.setRunningText(dto.getRunningText());
    }
}