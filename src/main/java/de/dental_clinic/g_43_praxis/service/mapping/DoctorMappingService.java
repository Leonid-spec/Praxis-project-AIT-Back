package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.DoctorDto;
import de.dental_clinic.g_43_praxis.domain.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",  uses = {ImageMappingService.class})
public interface DoctorMappingService {

    DoctorDto mapEntityToDto(Doctor entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Doctor mapDtoToEntity(DoctorDto dto);
}
