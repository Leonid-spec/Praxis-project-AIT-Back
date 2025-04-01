package de.dental_clinic.g_43_praxis.controller;
import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceValidationException;
import de.dental_clinic.g_43_praxis.service.interfaces.DentalServiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DentalServiceController {

    private final DentalServiceService dentalServiceService;

    @GetMapping("/services/active")
    public ResponseEntity<List<DentalServiceDto>> getActiveDentalServices() {
        List<DentalServiceDto> activeDentalServices = dentalServiceService.getActiveDentalServices();
        return ResponseEntity.ok(activeDentalServices);
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<DentalServiceDto> getDentalServiceById(@PathVariable Long id) {
        DentalServiceDto dentalServiceDto = dentalServiceService.getDentalServiceById(id);
        return ResponseEntity.ok(dentalServiceDto);
    }

    @GetMapping("/services")
    public ResponseEntity<List<DentalServiceDto>> getAllDentalServices() {
        List<DentalServiceDto> dentalServices = dentalServiceService.getAllDentalServices();
        return ResponseEntity.ok(dentalServices);
    }

    @PostMapping("/service")
    public ResponseEntity<DentalServiceDto> addDentalService(@Valid @RequestBody DentalServiceDto dentalServiceDto) {
        DentalServiceDto newDentalService = dentalServiceService.addDentalService(dentalServiceDto);
        return new ResponseEntity<>(newDentalService, HttpStatus.CREATED);
    }

    @PutMapping("/service")
    public ResponseEntity<DentalServiceDto> updateDentalService(@Valid @RequestBody DentalServiceDto dentalServiceDto) {
        Long id = dentalServiceDto.getId();

        if (id == null) {
            throw new DentalServiceValidationException("ID must be provided in the request body");
        }

        DentalServiceDto updatedDentalService = dentalServiceService.updateDentalService(id, dentalServiceDto);
        return ResponseEntity.ok(updatedDentalService);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDentalService(@PathVariable Long id) {
//        dentalServiceService.deleteDentalServiceById(id);
//        return ResponseEntity.noContent().build();
//    }
}
