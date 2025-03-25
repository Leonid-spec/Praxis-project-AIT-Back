package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.domain.entity.DentalService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DentalServiceMappingService {

    @Mapping(target = "images", source = "images")
    DentalServiceDto mapEntityToDto(DentalService entity);

    @Mapping(target = "images", source = "images")
    DentalService mapDtoToEntity(DentalServiceDto dto);
}

