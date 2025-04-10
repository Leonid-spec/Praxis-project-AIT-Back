package de.dental_clinic.g_43_praxis.service.interfaces;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.domain.dto.ChangePasswordDto;
import de.dental_clinic.g_43_praxis.domain.entity.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Optional<AdminDto> findByLogin(String login);
    AdminDto createAdmin(AdminDto dto);
    void changePassword(ChangePasswordDto dto);
    List<AdminDto> findAllAdmins();
    AdminDto deleteAdmin(AdminDto adminDto);
    public void validateAdminDto(AdminDto adminDto);
    public boolean createRoot();
    AdminDto killAdmin(String login);
}
