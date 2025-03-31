package de.dental_clinic.g_43_praxis.controller;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.service.interfaces.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Transactional
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/admin")
    public ResponseEntity<String> createAdmin(@RequestBody AdminDto adminDto) {
        adminService.createAdmin(adminDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin created successfully");
    }

    @PatchMapping("/mypassword")
    public ResponseEntity<String> changePassword(@RequestBody AdminDto adminDto) {
        adminService.changePassword(adminDto);
        return ResponseEntity.ok("Password updated successfully");
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<AdminDto> admins = adminService.findAllAdmins();
        return ResponseEntity.ok(admins);
    }

//    For future
//    /**
//     * Delete an administrator by their ID.
//     *
//     * @param id the ID of the administrator to delete
//     * @return success message if the admin was deleted
//     */
//    @Operation(summary = "Delete an admin", description = "Delete an administrator by their unique ID.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Admin deleted successfully",
//                    content = @Content(mediaType = "application/json")),
//            @ApiResponse(responseCode = "404", description = "Admin not found",
//                    content = @Content(mediaType = "application/json"))
//    })
//    @DeleteMapping("/admin/{id}")
//    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
//        adminService.deleteAdmin(id);
//        return ResponseEntity.ok("Admin deleted successfully");
//    }
}

