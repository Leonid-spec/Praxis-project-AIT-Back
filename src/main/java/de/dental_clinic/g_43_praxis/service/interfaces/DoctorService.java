package de.dental_clinic.g_43_praxis.service.interfaces;

import de.dental_clinic.g_43_praxis.domain.dto.DoctorDto;

import java.util.List;

public interface DoctorService {

    List<DoctorDto> getActiveDoctors();

    List<DoctorDto> getAllDoctors();

    DoctorDto getDoctorById(Long id);

    DoctorDto addDoctor(DoctorDto doctorDto);

    DoctorDto updateDoctor(DoctorDto doctorDto);

    DoctorDto deleteDoctor(Long id);

//    void deleteDoctorById(Long id);
}
