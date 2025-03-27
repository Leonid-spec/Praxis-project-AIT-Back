package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.domain.entity.Admin;
import de.dental_clinic.g_43_praxis.repository.AdminRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.AdminService;
import de.dental_clinic.g_43_praxis.service.mapping.AdminMappingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// All methods except login are in developing

@Service
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
    public void createAdmin(AdminDto dto) {
        adminRepository.save(adminMappingService.mapDtoToEntity(dto));
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
