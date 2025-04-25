package de.dental_clinic.g_43_praxis.controller;

import de.dental_clinic.g_43_praxis.domain.dto.SiteSettingsDto;
import de.dental_clinic.g_43_praxis.service.interfaces.SiteSettingsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final SiteSettingsService siteSettingsService;

    @GetMapping
    public ResponseEntity<SiteSettingsDto> getSettings() {
        return ResponseEntity.ok(siteSettingsService.getSettings());
    }

    @PutMapping
    public ResponseEntity<SiteSettingsDto> updateSettings(@RequestBody @Valid SiteSettingsDto dto) {
        return ResponseEntity.ok(siteSettingsService.updateSettings(dto));
    }
}