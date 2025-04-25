package de.dental_clinic.g_43_praxis.repository;


import de.dental_clinic.g_43_praxis.domain.entity.SiteSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteSettingsRepository extends JpaRepository<SiteSettings, Long> {
}

