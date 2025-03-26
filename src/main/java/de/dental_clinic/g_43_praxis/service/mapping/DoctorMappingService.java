package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.DoctorDto;
import de.dental_clinic.g_43_praxis.domain.dto.ImageDto;
import de.dental_clinic.g_43_praxis.domain.entity.Doctor;
import de.dental_clinic.g_43_praxis.domain.entity.Image;
import org.springframework.stereotype.Service;

@Service
public class DoctorMappingService {

    public DoctorDto mapEntityToDto(Doctor doctor) {
        return DoctorDto.builder()
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
                .images(doctor.getImages().stream().map(this::toImageDto).toList())
                .build();
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
        doctor.setActive(dto.isActive());
        doctor.setImages(dto.getImages().stream().map(this::toImageEntity).toList());
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

    private Image toImageEntity(ImageDto dto) {
        Image image = new Image();
        image.setId(dto.getId());
        image.setPath(dto.getPath());
        return image;
    }

}
