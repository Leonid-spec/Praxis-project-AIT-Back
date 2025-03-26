package de.dental_clinic.g_43_praxis.service.interfaces;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.domain.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Optional<AdminDto> findByLogin(String login);
    void createAdmin(AdminDto dto);
    void changePassword(AdminDto dto);
    List<AdminDto> findAllAdmins();
    void deleteAdmin(Long id);
}
