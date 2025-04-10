package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.domain.dto.ImageDto;
import de.dental_clinic.g_43_praxis.domain.entity.Image;
import de.dental_clinic.g_43_praxis.domain.entity.DentalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DentalServiceMappingService {

    public DentalServiceDto mapEntityToDto(DentalService dentalService) {
        DentalServiceDto dentalServiceDto =  DentalServiceDto.builder()
                .id(dentalService.getId())
                .titleDe(dentalService.getTitleDe())
                .titleEn(dentalService.getTitleEn())
                .titleRu(dentalService.getTitleRu())
                .descriptionDe(dentalService.getDescriptionDe())
                .descriptionEn(dentalService.getDescriptionEn())
                .descriptionRu(dentalService.getDescriptionRu())
                .topImage(dentalService.getTopImage())
                .isActive(dentalService.getIsActive())
                .images(new ArrayList<>())
                .build();
        if(  (dentalService.getImages() != null) )
        {
            for(Image image : dentalService.getImages()) {
                if(image != null) {
                    dentalServiceDto.getImages().add(this.toImageDto(image));
                }
            }
        }
        return dentalServiceDto;
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
        dentalService.setImages(new ArrayList<>());
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
}
