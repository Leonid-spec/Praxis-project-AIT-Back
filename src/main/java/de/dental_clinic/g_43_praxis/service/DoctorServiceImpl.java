package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.DoctorDto;
import de.dental_clinic.g_43_praxis.domain.entity.Doctor;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DoctorAlreadyExistsException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DoctorNotFoundException;
import de.dental_clinic.g_43_praxis.repository.DoctorRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.DoctorService;
import de.dental_clinic.g_43_praxis.service.mapping.DoctorMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<DoctorDto> getActiveDoctors() {
        List<Doctor> activeDoctors = doctorRepository.findAllByIsActiveTrue();
        return activeDoctors.stream()
                .map(doctorMappingService::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMappingService::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + id + " not found"));
        return doctorMappingService.mapEntityToDto(doctor);
    }

    @Override
    public DoctorDto addDoctor(DoctorDto doctorDto) {
        if (doctorRepository.existsByFullName(doctorDto.getFullName())) {
            throw new DoctorAlreadyExistsException("Doctor with name " + doctorDto.getFullName() + " already exists");
        }

        Doctor doctor = doctorMappingService.mapDtoToEntity(doctorDto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMappingService.mapEntityToDto(savedDoctor);
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) {
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
        doctor.setActive(doctorDto.isActive());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMappingService.mapEntityToDto(updatedDoctor);
    }

    @Override
    public void deleteDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + id + " not found"));
        doctorRepository.deleteById(id);

    }
}
