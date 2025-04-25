package de.dental_clinic.g_43_praxis.service.interfaces;

import de.dental_clinic.g_43_praxis.domain.dto.SiteSettingsDto;

public interface SiteSettingsService {

    SiteSettingsDto getSettings();

    SiteSettingsDto updateSettings(SiteSettingsDto dto);
}
