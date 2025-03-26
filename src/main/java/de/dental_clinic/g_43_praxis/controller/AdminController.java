package de.dental_clinic.g_43_praxis.controller;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.domain.entity.Admin;
import de.dental_clinic.g_43_praxis.exception_handling.Response;
import de.dental_clinic.g_43_praxis.repository.AdminRepository;
import de.dental_clinic.g_43_praxis.service.AdminServiceImpl;
import de.dental_clinic.g_43_praxis.service.mapping.AdminMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public class AdminController {

    private final AdminServiceImpl adminService;
    private final AdminRepository adminRepository;

    public AdminController(AdminServiceImpl adminService, AdminRepository adminRepository) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> checkAdmin(@RequestBody AdminDto adminDto) {
        adminService.login(adminDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}