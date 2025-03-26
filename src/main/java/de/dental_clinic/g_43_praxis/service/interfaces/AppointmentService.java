package de.dental_clinic.g_43_praxis.service.interfaces;

import de.dental_clinic.g_43_praxis.domain.dto.AppointmentDto;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    AppointmentDto createAppointment(AppointmentDto appointmentDto);

    List<AppointmentDto> getAllAppointments();

    Optional<AppointmentDto> getAppointmentById(Long id);

    AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto);

    boolean deleteAppointment(Long id);
}
