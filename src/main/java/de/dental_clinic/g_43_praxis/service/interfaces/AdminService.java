package de.dental_clinic.g_43_praxis.service.interfaces;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    void login(AdminDto adminDto);
    Optional<AdminDto> findByLogin(String login);
    List<AdminDto> getAllAdmins();
    void deleteAdmin(Long id);
}
