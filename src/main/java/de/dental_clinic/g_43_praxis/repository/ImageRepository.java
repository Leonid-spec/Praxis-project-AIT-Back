package de.dental_clinic.g_43_praxis.repository;

import de.dental_clinic.g_43_praxis.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
