package de.dental_clinic.g_43_praxis.repository;

import de.dental_clinic.g_43_praxis.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByStatus(String status);

    List<Appointment> findAllByService_Id(Long dentalServiceId);

    List<Appointment> findByIsActive(Boolean isActive);

}
