package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.ImageDto;
import de.dental_clinic.g_43_praxis.domain.entity.Image;
import org.springframework.stereotype.Service;

@Service
public class ImageMappingService {

    public ImageDto mapEntityToDto(Image image) {
        return ImageDto.builder()
                .id(image.getId())
                .path(image.getPath())
                .dentalServiceId(image.getDentalService() != null ? image.getDentalService().getId() : null)
                .doctorId(image.getDoctor() != null ? image.getDoctor().getId() : null)
                .build();
    }

    public Image mapDtoToEntity(ImageDto dto) {
        Image image = new Image();
        image.setId(dto.getId());
        image.setPath(dto.getPath());
        return image;
    }
}
