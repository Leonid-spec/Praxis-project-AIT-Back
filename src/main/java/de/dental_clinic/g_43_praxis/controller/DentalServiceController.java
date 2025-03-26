package de.dental_clinic.g_43_praxis.controller;
import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceValidationException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DoctorAlreadyExistsException;
import de.dental_clinic.g_43_praxis.service.interfaces.DentalServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@AllArgsConstructor
@Tag(name = "Dental Services", description = "API for managing dental services")
public class DentalServiceController {

    private final DentalServiceService dentalServiceService;

    @Operation(summary = "Get active dental services", description = "Retrieve a list of active dental services")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/active")
    public ResponseEntity<List<DentalServiceDto>> getActiveDentalServices() {
        List<DentalServiceDto> activeDentalServices = dentalServiceService.getActiveDentalServices();
        return ResponseEntity.ok(activeDentalServices);
    }

    @Operation(summary = "Get dental service by ID", description = "Retrieve a dental service using its unique ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Dental service not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DentalServiceDto> getDentalServiceById(@PathVariable Long id) {
        DentalServiceDto dentalServiceDto = dentalServiceService.getDentalServiceById(id);
        return ResponseEntity.ok(dentalServiceDto);
    }

    @Operation(summary = "Get all dental services", description = "Retrieve all dental services (admin only)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping
    public ResponseEntity<List<DentalServiceDto>> getAllDentalServices() {
        List<DentalServiceDto> dentalServices = dentalServiceService.getAllDentalServices();
        return ResponseEntity.ok(dentalServices);
    }

    @Operation(summary = "Create a new dental service", description = "Add a new dental service to the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dental service created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "409", description = "Dental service already exists")
    })
    @PostMapping
    public ResponseEntity<DentalServiceDto> addDentalService(@RequestBody DentalServiceDto dentalServiceDto) {
        DentalServiceDto newDentalService = dentalServiceService.addDentalService(dentalServiceDto);
        return ResponseEntity.ok(newDentalService);
    }

    @Operation(summary = "Update dental service", description = "Update an existing dental service")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dental service updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Dental service not found")
    })
    @PutMapping()
    public ResponseEntity<DentalServiceDto> updateDentalService(@RequestBody DentalServiceDto dentalServiceDto) {
        Long id = dentalServiceDto.getId();

        if (id == null) {
            throw new DentalServiceValidationException("ID must be provided in the request body");
        }

        DentalServiceDto updatedDentalService = dentalServiceService.updateDentalService(id, dentalServiceDto);
        return ResponseEntity.ok(updatedDentalService);
    }

    @Operation(summary = "Delete dental service", description = "Delete a dental service using its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Dental service deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Dental service not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        dentalServiceService.deleteDentalServiceById(id);
        return ResponseEntity.noContent().build();
    }
}
