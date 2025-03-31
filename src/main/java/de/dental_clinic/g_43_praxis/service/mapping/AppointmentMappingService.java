package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.AppointmentDto;
import de.dental_clinic.g_43_praxis.domain.entity.Appointment;
import org.springframework.stereotype.Service;

@Service
public class AppointmentMappingService {

    public AppointmentDto mapToDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();

        appointmentDto.setId(appointment.getId());
        appointmentDto.setDentalServiceId(appointment.getService().getId());
        appointmentDto.setFirstName(appointment.getFirstName());
        appointmentDto.setLastName(appointment.getLastName());
        appointmentDto.setPhone1(appointment.getPhone1());
        appointmentDto.setPhone2(appointment.getPhone2());
        appointmentDto.setEmail(appointment.getEmail());
        appointmentDto.setAvailableTime(appointment.getAvailableTime());
        appointmentDto.setComment(appointment.getComment());
        appointmentDto.setLanguage(appointment.getLanguage());
        appointmentDto.setIsNew(appointment.getIsNew());
//        appointmentDto.setStatus(appointment.getStatus());
        return appointmentDto;
    }

    public void mapDtoToEntity(AppointmentDto appointmentDto, Appointment appointment) {
        appointment.setFirstName(appointmentDto.getFirstName());
        appointment.setLastName(appointmentDto.getLastName());
        appointment.setPhone1(appointmentDto.getPhone1());
        appointment.setPhone2(appointmentDto.getPhone2());
        appointment.setEmail(appointmentDto.getEmail());
        appointment.setAvailableTime(appointmentDto.getAvailableTime());
        appointment.setComment(appointmentDto.getComment());
        appointment.setLanguage(appointmentDto.getLanguage());
        appointment.setIsNew(appointmentDto.getIsNew());
//        appointment.setStatus(appointmentDto.getStatus());
    }

}
