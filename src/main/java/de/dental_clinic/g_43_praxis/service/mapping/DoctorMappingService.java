package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.DoctorDto;
import de.dental_clinic.g_43_praxis.domain.dto.ImageDto;
import de.dental_clinic.g_43_praxis.domain.entity.Doctor;
import de.dental_clinic.g_43_praxis.domain.entity.Image;
import de.dental_clinic.g_43_praxis.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DoctorMappingService {

    private final ImageRepository imageRepository;

    public DoctorMappingService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public DoctorDto mapEntityToDto(Doctor doctor) {
        DoctorDto doctorDto = DoctorDto.builder()
                .id(doctor.getId())
                .fullName(doctor.getFullName())
                .titleDe(doctor.getTitleDe())
                .titleEn(doctor.getTitleEn())
                .titleRu(doctor.getTitleRu())
                .biographyDe(doctor.getBiographyDe())
                .biographyEn(doctor.getBiographyEn())
                .biographyRu(doctor.getBiographyRu())
                .specialisationDe(doctor.getSpecialisationDe())
                .specialisationEn(doctor.getSpecialisationEn())
                .specialisationRu(doctor.getSpecialisationRu())
                .topImage(doctor.getTopImage())
                .isActive(doctor.isActive())
                .images(new ArrayList<>())
                .build();
        if(  (doctor.getImages() != null) )
        {
            for(Image image : doctor.getImages()) {
                if(image != null) {
                    doctorDto.getImages().add(this.toImageDto(image));
                }
            }
        }
        return doctorDto;
    }

    public Doctor mapDtoToEntity(DoctorDto dto) {
        Doctor doctor = new Doctor();
        doctor.setId(dto.getId());
        doctor.setFullName(dto.getFullName());
        doctor.setTitleDe(dto.getTitleDe());
        doctor.setTitleEn(dto.getTitleEn());
        doctor.setTitleRu(dto.getTitleRu());
        doctor.setBiographyDe(dto.getBiographyDe());
        doctor.setBiographyEn(dto.getBiographyEn());
        doctor.setBiographyRu(dto.getBiographyRu());
        doctor.setSpecialisationDe(dto.getSpecialisationDe());
        doctor.setSpecialisationEn(dto.getSpecialisationEn());
        doctor.setSpecialisationRu(dto.getSpecialisationRu());
        doctor.setTopImage(dto.getTopImage());
        doctor.setActive(dto.getIsActive());
        doctor.setImages(new ArrayList<>());
        return doctor;
    }

    private ImageDto toImageDto(Image image) {
        return ImageDto.builder()
                .id(image.getId())
                .path(image.getPath())
                .dentalServiceId(image.getDentalService() != null ? image.getDentalService().getId() : null)
                .doctorId(image.getDoctor() != null ? image.getDoctor().getId() : null)
                .build();
    }
}
