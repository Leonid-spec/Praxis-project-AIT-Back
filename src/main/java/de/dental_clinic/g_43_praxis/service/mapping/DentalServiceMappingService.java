package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.domain.entity.DentalService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ImageMappingService.class})
public interface DentalServiceMappingService {

    DentalServiceDto mapEntityToDto(DentalService entity);

    DentalService mapDtoToEntity(DentalServiceDto dto);
}

