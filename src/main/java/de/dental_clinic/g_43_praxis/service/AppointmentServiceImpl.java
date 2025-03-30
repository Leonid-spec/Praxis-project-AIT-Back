package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.AppointmentDto;
import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.domain.entity.Appointment;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.AppointmentNotFoundException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceNotFoundException;
import de.dental_clinic.g_43_praxis.repository.AppointmentRepository;
import de.dental_clinic.g_43_praxis.repository.DentalServiceRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.AppointmentService;
import de.dental_clinic.g_43_praxis.service.mapping.AppointmentMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        validateAppointmentDto(appointmentDto);
        Appointment appointment = new Appointment();
        appointmentMappingService.mapDtoToEntity(appointmentDto, appointment);
        appointment.setService(dentalServiceRepository.findById(appointmentDto.getDentalServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid dental service ID")));

        appointment = appointmentRepository.save(appointment);
        return appointmentMappingService.mapToDto(appointment);
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMappingService::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AppointmentDto> getAppointmentById(Long id) {
        validateId(id);
        AppointmentService appointmentService = (AppointmentService) appointmentRepository.findById(id)
                .orElseThrow(() -> new DentalServiceNotFoundException("Appointment with ID " + id + " not found"));
        return appointmentRepository.findById(id)
                .map(appointmentMappingService::mapToDto);
    }

    @Override
    public AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto) {
        validateId(id);
        validateAppointmentDto(appointmentDto);
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));

        appointmentMappingService.mapDtoToEntity(appointmentDto, appointment);
        appointment.setService(dentalServiceRepository.findById(appointmentDto.getDentalServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid dental service ID")));

        appointment = appointmentRepository.save(appointment);
        return appointmentMappingService.mapToDto(appointment);
    }

//    @Override
//    public boolean changeAppointmentStatus(Long id, boolean isActive) {
//        Appointment appointment = appointmentRepository.findById(id)
//                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));
//
//        appointment.setIsActive(isActive);
//        appointmentRepository.save(appointment);
//        return isActive;
//    }

//    @Override
//    public List<AppointmentDto> getActiveAppointments() {
//        return appointmentRepository.findByIsActive(true).stream()
//                .map(appointmentMappingService::mapToDto)
//                .collect(Collectors.toList());
//    }

//    @Override
    public boolean deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
        return true;
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: ID must be a positive number.");
        }
    }

    private void validateAppointmentDto(AppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            throw new IllegalArgumentException("Field for appointmentDto cannot be null.");
        }
        if (!StringUtils.hasText(appointmentDto.getFirstName())) {
            throw new IllegalArgumentException("Field firstName cannot be null or empty.");
        }
        if (!StringUtils.hasText(appointmentDto.getLastName())) {
            throw new IllegalArgumentException("Field lastName cannot be null or empty.");
        }
        if (!StringUtils.hasText(appointmentDto.getPhone1())) {
            throw new IllegalArgumentException("Field phone1 cannot be null or empty.");
        }
        if (!StringUtils.hasText(appointmentDto.getEmail())) {
            throw new IllegalArgumentException("Field email cannot be null or empty.");
        }
        if (!StringUtils.hasText(String.valueOf(appointmentDto.getIsNew()))) {
            throw new IllegalArgumentException("Field isNew cannot be null or empty.");
        }
    }
}
