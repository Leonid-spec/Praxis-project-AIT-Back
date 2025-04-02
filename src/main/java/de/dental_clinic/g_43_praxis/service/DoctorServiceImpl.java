package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.AppointmentDto;
import de.dental_clinic.g_43_praxis.domain.dto.DoctorDto;
import de.dental_clinic.g_43_praxis.domain.entity.Doctor;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DoctorAlreadyExistsException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DoctorNotFoundException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DoctorValidationException;
import de.dental_clinic.g_43_praxis.repository.DoctorRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.DoctorService;
import de.dental_clinic.g_43_praxis.service.mapping.DoctorMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMappingService doctorMappingService;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMappingService doctorMappingService) {
        this.doctorRepository = doctorRepository;
        this.doctorMappingService = doctorMappingService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DoctorDto> getActiveDoctors() {
        List<Doctor> activeDoctors = doctorRepository.findAllByIsActiveTrue();
        return activeDoctors.stream()
                .map(doctorMappingService::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMappingService::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto getDoctorById(Long id) {
        validateId(id);
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + id + " not found"));
        return doctorMappingService.mapEntityToDto(doctor);
    }

    @Transactional
    @Override
    public DoctorDto addDoctor(DoctorDto doctorDto) {
        validateAppointmentDto(doctorDto);
        if (doctorRepository.existsByFullName(doctorDto.getFullName())) {
            throw new DoctorValidationException("Doctor with name " + doctorDto.getFullName() + " already exists");
        }

        Doctor doctor = doctorMappingService.mapDtoToEntity(doctorDto);
        doctor.setId(null);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMappingService.mapEntityToDto(savedDoctor);
    }

    @Transactional
    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) {
        validateId(id);
        validateAppointmentDto(doctorDto);
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + id + " not found"));

        doctor.setFullName(doctorDto.getFullName());
        doctor.setTitleDe(doctorDto.getTitleDe());
        doctor.setTitleEn(doctorDto.getTitleEn());
        doctor.setTitleRu(doctorDto.getTitleRu());
        doctor.setBiographyDe(doctorDto.getBiographyDe());
        doctor.setBiographyEn(doctorDto.getBiographyEn());
        doctor.setBiographyRu(doctorDto.getBiographyRu());
        doctor.setSpecialisationDe(doctorDto.getSpecialisationDe());
        doctor.setSpecialisationEn(doctorDto.getSpecialisationEn());
        doctor.setSpecialisationRu(doctorDto.getSpecialisationRu());
        doctor.setTopImage(doctorDto.getTopImage());
//        doctor.setActive(doctorDto.isActive());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMappingService.mapEntityToDto(updatedDoctor);
    }

//    @Override
//    public void deleteDoctorById(Long id) {
//        Doctor doctor = doctorRepository.findById(id)
//                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + id + " not found"));
//        doctorRepository.deleteById(id);
//
//    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new DoctorValidationException("Invalid ID: ID must be a positive number.");
        }
    }

    private void validateAppointmentDto(DoctorDto doctorDto) {
        if (doctorDto == null) {
            throw new DoctorValidationException("Field for doctorDto cannot be null.");
        }
        if (!StringUtils.hasText(doctorDto.getFullName())) {
            throw new DoctorValidationException("Field fullName cannot be null or empty.");
        }
        if (!StringUtils.hasText(doctorDto.getTitleDe())) {
            throw new DoctorValidationException("Field titleDe cannot be null or empty.");
        }
        if (!StringUtils.hasText(doctorDto.getTitleEn())) {
            throw new DoctorValidationException("Field titleEn cannot be null or empty.");
        }
        if (!StringUtils.hasText(doctorDto.getTitleRu())) {
            throw new DoctorValidationException("Field titleRu cannot be null or empty.");
        }
        if (!StringUtils.hasText(doctorDto.getBiographyDe())) {
            throw new DoctorValidationException("Field biographyDe cannot be null or empty.");
        }
        if (!StringUtils.hasText(doctorDto.getBiographyEn())) {
            throw new DoctorValidationException("Field biographyEn cannot be null or empty.");
        }
        if (!StringUtils.hasText(doctorDto.getBiographyRu())) {
            throw new DoctorValidationException("Field biographyRu cannot be null or empty.");
        }
        if (!StringUtils.hasText(doctorDto.getSpecialisationDe())) {
            throw new DoctorValidationException("Field specialisationDe cannot be null or empty.");
        }
        if (!StringUtils.hasText(doctorDto.getSpecialisationEn())) {
            throw new DoctorValidationException("Field specialisationEn cannot be null or empty.");
        }
        if (!StringUtils.hasText(doctorDto.getSpecialisationRu())) {
            throw new DoctorValidationException("Field specialisationRu cannot be null or empty.");
        }
    }
}
