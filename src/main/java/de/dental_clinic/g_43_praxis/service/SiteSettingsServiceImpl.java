package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.SiteSettingsDto;
import de.dental_clinic.g_43_praxis.domain.entity.SiteSettings;
import de.dental_clinic.g_43_praxis.repository.SiteSettingsRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.SiteSettingsService;
import de.dental_clinic.g_43_praxis.service.mapping.SiteSettingsMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteSettingsServiceImpl implements SiteSettingsService {

    private final SiteSettingsRepository repository;
    private final SiteSettingsMappingService mappingService;

    @Override
    public SiteSettingsDto getSettings() {
        SiteSettings settings = repository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Site settings not found"));
        return mappingService.toDto(settings);
    }

    @Override
    public SiteSettingsDto updateSettings(SiteSettingsDto dto) {
        SiteSettings settings = repository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Site settings not found"));

        mappingService.updateEntityFromDto(dto, settings);
        repository.save(settings);
        return mappingService.toDto(settings);
    }
}