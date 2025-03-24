package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.domain.entity.DentalService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DentalServiceMappingService {

    DentalServiceDto mapEntityToDto(DentalService entity);

    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "active", ignore = true)
    DentalService mapDtoToEntity(DentalServiceDto dto);
}
