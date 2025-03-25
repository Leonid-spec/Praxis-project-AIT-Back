package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.domain.dto.ImageDto;
import de.dental_clinic.g_43_praxis.domain.entity.Image;
import de.dental_clinic.g_43_praxis.domain.entity.DentalService;
import org.springframework.stereotype.Service;

@Service
public class DentalServiceMappingService {

    public DentalServiceDto mapEntityToDto(DentalService dentalService) {
        return DentalServiceDto.builder()
                .id(dentalService.getId())
                .titleDe(dentalService.getTitleDe())
                .titleEn(dentalService.getTitleEn())
                .titleRu(dentalService.getTitleRu())
                .descriptionDe(dentalService.getDescriptionDe())
                .descriptionEn(dentalService.getDescriptionEn())
                .descriptionRu(dentalService.getDescriptionRu())
                .topImage(dentalService.getTopImage())
                .isActive(dentalService.getIsActive())
                .images(dentalService.getImages().stream().map(this::toImageDto).toList())
                .build();
    }

    public DentalService mapDtoToEntity(DentalServiceDto dto) {
        DentalService dentalService = new DentalService();
        dentalService.setId(dto.getId());
        dentalService.setTitleDe(dto.getTitleDe());
        dentalService.setTitleEn(dto.getTitleEn());
        dentalService.setTitleRu(dto.getTitleRu());
        dentalService.setDescriptionDe(dto.getDescriptionDe());
        dentalService.setDescriptionEn(dto.getDescriptionEn());
        dentalService.setDescriptionRu(dto.getDescriptionRu());
        dentalService.setTopImage(dto.getTopImage());
        dentalService.setIsActive(dto.getIsActive());
        dentalService.setImages(dto.getImages().stream().map(this::toImageEntity).toList());
        return dentalService;
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
