package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.domain.entity.Admin;
import de.dental_clinic.g_43_praxis.repository.AdminRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.AdminService;
import de.dental_clinic.g_43_praxis.service.mapping.AdminMappingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMappingService adminMappingService;

    public AdminServiceImpl(AdminRepository adminRepository, AdminMappingService adminMappingService) {
        this.adminRepository = adminRepository;
        this.adminMappingService = adminMappingService;
    }

    public Optional<AdminDto> findByLogin(String login) {
        Optional<Admin> admin = adminRepository.findByLogin(login);

        return admin.map(a -> {
            AdminDto adminDto = new AdminDto();
            adminDto.setLogin(a.getLogin());
            adminDto.setPassword(a.getPassword());
            return adminDto;
        });
    }

    @Override
    public void login(AdminDto adminDto) {
        Admin admin = adminMappingService.mapDtoToEntity(adminDto);

    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return List.of();
    }

    @Override
    public void deleteAdmin(Long id) {

    }
}
