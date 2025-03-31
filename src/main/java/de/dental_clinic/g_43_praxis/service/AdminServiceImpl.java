package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.domain.entity.Admin;
import de.dental_clinic.g_43_praxis.domain.entity.Role;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.AdminAlreadyExistsException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.AdminNotFoundException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceAlreadyExistsException;
import de.dental_clinic.g_43_praxis.repository.AdminRepository;
import de.dental_clinic.g_43_praxis.repository.RoleRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.AdminService;
import de.dental_clinic.g_43_praxis.service.mapping.AdminMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMappingService adminMappingService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<AdminDto> findByLogin(String login) {
        return adminRepository.findByLogin(login)
                .map(adminMappingService::mapEntityToDto);
    }

    @Override
    public void createAdmin(AdminDto dto) {
        if (adminRepository.findByLogin(dto.getLogin()).isPresent()) {
            throw new AdminAlreadyExistsException("Admin already exists");
        }
        Admin admin = adminMappingService.mapDtoToEntity(dto);
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        adminRepository.save(admin);
    }


    @Override
    public void changePassword(AdminDto dto) {
        Optional<Admin> adminOptional = adminRepository.findByLogin(dto.getLogin());
        if (adminOptional.isEmpty()) {
            throw new IllegalArgumentException("Admin with login '" + dto.getLogin() + "' does not exist");
        }
        Admin admin = adminOptional.get();
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        adminRepository.save(admin);
    }

    @Override
    public List<AdminDto> findAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(adminMappingService::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
        public void deleteAdmin(Long id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isEmpty()) {
            throw new AdminNotFoundException("Admin with this ID does not exist");
        }
        Admin admin = adminOptional.get();
        admin.getRoles().clear();
        adminRepository.save(admin);
        adminRepository.deleteById(id);
    }
}
