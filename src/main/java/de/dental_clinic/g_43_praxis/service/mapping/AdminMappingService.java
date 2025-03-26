package de.dental_clinic.g_43_praxis.service.mapping;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.domain.entity.Admin;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AdminMappingService {

    public AdminDto mapEntityToDto(Admin entity) {
        if (entity == null) {
            return null;
        }

        AdminDto dto = new AdminDto();
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    public Admin mapDtoToEntity(AdminDto dto) {
        if (dto == null) {
            return null;
        }

        Admin admin = new Admin();
        admin.setLogin(dto.getLogin());
        admin.setPassword(dto.getPassword());
        admin.setRoles(new HashSet<>());

        return admin;
    }
}