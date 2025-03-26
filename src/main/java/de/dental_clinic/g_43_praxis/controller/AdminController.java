package de.dental_clinic.g_43_praxis.controller;

import de.dental_clinic.g_43_praxis.domain.dto.AdminDto;
import de.dental_clinic.g_43_praxis.service.interfaces.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * Create a new administrator.
     *
     * @param adminDto the administrator data to be created
     * @return success message if the admin was created
     */
    @Operation(summary = "Create a new admin", description = "Create a new administrator with provided login and password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin created successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request - Admin with the provided login already exists",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/admin")
    public ResponseEntity<String> createAdmin(@RequestBody AdminDto adminDto) {
        adminService.createAdmin(adminDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin created successfully");
    }

    /**
     * Change the password of an existing administrator.
     *
     * @param adminDto the administrator login and the new password
     * @return success message if the password was updated
     */
    @Operation(summary = "Change admin password", description = "Update the password for an existing administrator using their login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password updated successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Admin not found",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/mypassword")
    public ResponseEntity<String> changePassword(@RequestBody AdminDto adminDto) {
        adminService.changePassword(adminDto);
        return ResponseEntity.ok("Password updated successfully");
    }

    /**
     * Retrieve a list of all administrators.
     *
     * @return a list of all administrators
     */
    @Operation(summary = "Get all admins", description = "Retrieve a list of all administrators without passwords.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of admins",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdminDto.class)))
    })
    @GetMapping("/admins")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<AdminDto> admins = adminService.findAllAdmins();
        return ResponseEntity.ok(admins);
    }

    /**
     * Delete an administrator by their ID.
     *
     * @param id the ID of the administrator to delete
     * @return success message if the admin was deleted
     */
    @Operation(summary = "Delete an admin", description = "Delete an administrator by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin deleted successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Admin not found",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok("Admin deleted successfully");
    }
}

