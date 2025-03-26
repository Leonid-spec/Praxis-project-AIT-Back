package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.AppointmentDto;
import de.dental_clinic.g_43_praxis.domain.entity.Appointment;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.AppointmentNotFoundException;
import de.dental_clinic.g_43_praxis.repository.AppointmentRepository;
import de.dental_clinic.g_43_praxis.repository.DentalServiceRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.AppointmentService;
import de.dental_clinic.g_43_praxis.service.mapping.AppointmentMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DentalServiceRepository dentalServiceRepository;
    private final AppointmentMappingService appointmentMappingService;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  DentalServiceRepository dentalServiceRepository,
                                  AppointmentMappingService appointmentMappingService) {
        this.appointmentRepository = appointmentRepository;
        this.dentalServiceRepository = dentalServiceRepository;
        this.appointmentMappingService = appointmentMappingService;
    }

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointmentMappingService.mapDtoToEntity(appointmentDto, appointment); // Маппинг DTO в сущность
        appointment.setService(dentalServiceRepository.findById(appointmentDto.getDentalServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid dental service ID")));

        appointment = appointmentRepository.save(appointment);
        return appointmentMappingService.mapToDto(appointment); // Маппинг сущности в DTO
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMappingService::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AppointmentDto> getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(appointmentMappingService::mapToDto);
    }

    @Override
    public AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));

        appointmentMappingService.mapDtoToEntity(appointmentDto, appointment); // Маппинг DTO в сущность
        appointment.setService(dentalServiceRepository.findById(appointmentDto.getDentalServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid dental service ID")));

        appointment = appointmentRepository.save(appointment);
        return appointmentMappingService.mapToDto(appointment); // Маппинг сущности в DTO
    }

    @Override
    public boolean deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
        return true;
    }
}
