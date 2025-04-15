package de.dental_clinic.g_43_praxis.service;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.domain.dto.ChangePasswordDto;
import de.dental_clinic.g_43_praxis.domain.entity.Admin;
import de.dental_clinic.g_43_praxis.domain.entity.Role;
import de.dental_clinic.g_43_praxis.repository.AdminRepository;
import de.dental_clinic.g_43_praxis.repository.RoleRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.AdminService;
import de.dental_clinic.g_43_praxis.service.mapping.AdminMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMappingService adminMappingService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<AdminDto> findByLogin(String login) {
        return adminRepository.findByLogin( validateLogin(login))
                .map(adminMappingService::mapEntityToDto);
    }

    @Transactional
    @Override
    public boolean createRoot() {
        Admin root = new Admin();
        root.setLogin("root");
        root.setPassword(passwordEncoder.encode("Root1234"));
        root.getRoles().add(roleRepository.findByName("ROLE_ROOT" )
                .orElseThrow(() -> new IllegalArgumentException("FATAL ERROR")));
        root.getRoles().add(roleRepository.findByName("ROLE_ADMIN" )
                .orElseThrow(() -> new IllegalArgumentException("FATAL ERROR")));
        adminRepository.save(root);
        return true;
    }

    @Transactional
    @Override
    public AdminDto createAdmin(AdminDto dto) {
        validateAdminDto(dto);
        if (adminRepository.findByLogin(dto.getLogin()).isPresent()) {
            throw new IllegalArgumentException("Admin already exists.");
        }
        Admin admin = adminMappingService.mapDtoToEntity(dto);
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        admin.getRoles().add(roleRepository.findByName("ROLE_ADMIN" )
                .orElseThrow(() -> new IllegalArgumentException("FATAL ERROR")));
        return adminMappingService.mapEntityToDto(adminRepository.save(admin));
    }

    @Transactional
    @Override
    public void changePassword(ChangePasswordDto dto) {
        Admin admin = adminRepository.findByLogin(validateLogin(dto.getLogin()))
                .orElseThrow(() -> new IllegalArgumentException("Admin with login " + dto.getLogin() + " not found."));
        if (passwordEncoder.matches(dto.getOldPassword(), admin.getPassword())) {
            admin.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            adminRepository.save(admin);
        }
        else{throw new IllegalArgumentException("Admin with login " + dto.getLogin() + " not found."); }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminDto> findAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(adminMappingService::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public AdminDto killAdmin(String login) {
        Admin admin = adminRepository.findByLogin(validateLogin(login))
                .orElseThrow(() -> new IllegalArgumentException("Admin with login  not found"));
        if (admin.getRoles().contains(roleRepository.findByName("ROLE_ROOT").orElseThrow())  )
        {throw new IllegalArgumentException("Admin delete error"); }

        Long id = admin.getId();
        AdminDto adminDto = adminMappingService.mapEntityToDto(admin);
        admin.getRoles().clear();
        adminRepository.saveAndFlush(admin);
        adminRepository.delete(admin);
        if(adminRepository.findById(id).isPresent()) {throw new IllegalArgumentException("Admin delete error"); }
        return adminDto;
    }

    @Transactional
    @Override
        public AdminDto deleteAdmin(AdminDto adminDto) {
        validateAdminDto(adminDto);
        Admin admin = adminRepository.findByLogin(adminDto.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("Admin with login  not found"));
        if (!passwordEncoder.matches(adminDto.getPassword(), admin.getPassword()) ||
                admin.getRoles().contains(roleRepository.findByName("ROLE_ROOT").orElseThrow())  )
        {throw new IllegalArgumentException("Admin delete error"); }

        Long id = admin.getId();
        adminDto = adminMappingService.mapEntityToDto(admin);
        admin.getRoles().clear();
        adminRepository.saveAndFlush(admin);
        adminRepository.delete(admin);
        if(adminRepository.findById(id).isPresent()) {throw new IllegalArgumentException("Admin delete error"); }
        return adminDto;
    }

    @Override
    public void validateAdminDto(AdminDto adminDto) {
        if (adminDto == null) {
            throw new IllegalArgumentException("Field for adminDto cannot be null.");
        }

        adminDto.setLogin(validateLogin(adminDto.getLogin()));

        if (!StringUtils.hasText(adminDto.getPassword())) {
            throw new IllegalArgumentException("Field password cannot be null or empty.");
        }
    }

    @Override
    public String validateLogin(String login) {
        if (StringUtils.hasText(login)) { return login.toLowerCase().replaceAll("\\s+", ""); }
        else { throw new IllegalArgumentException("Field login cannot be null or empty."); }
    }
}
