package de.dental_clinic.g_43_praxis.controller;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.domain.dto.DoctorDto;
import de.dental_clinic.g_43_praxis.domain.entity.DentalService;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceNotFoundException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceValidationException;
import de.dental_clinic.g_43_praxis.repository.DentalServiceRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.DentalServiceService;
import de.dental_clinic.g_43_praxis.service.mapping.DentalServiceMappingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@AllArgsConstructor
public class DentalServiceController {

    private final DentalServiceService dentalServiceService;

    // Получить список активных услуг
    @GetMapping("/active")
    public ResponseEntity<List<DentalServiceDto>> getActiveDentalServices() {
        List<DentalServiceDto> activeDentalServices = dentalServiceService.getActiveDentalServices();
        return ResponseEntity.ok(activeDentalServices);
    }

    // Получить услугу по ID
    @GetMapping("/{id}")
    public ResponseEntity<DentalServiceDto> getDentalServiceById(@PathVariable Long id) {
        DentalServiceDto dentalServiceDto = dentalServiceService.getDentalServiceById(id);
        return ResponseEntity.ok(dentalServiceDto);
    }

    // Получить все услуги (для админа)
    @GetMapping
    public ResponseEntity<List<DentalServiceDto>> getAllDentalServices() {
        List<DentalServiceDto> dentalServices = dentalServiceService.getAllDentalServices();
        return ResponseEntity.ok(dentalServices);
    }

    // Создать новую услугу
    @PostMapping
    public ResponseEntity<DentalServiceDto> addDentalService(@RequestBody DentalServiceDto dentalServiceDto) {
        DentalServiceDto newDentalService = dentalServiceService.addDentalService(dentalServiceDto);
        return ResponseEntity.ok(newDentalService);
    }

    // Обновить данные услуги
    @PutMapping()
    public ResponseEntity<DentalServiceDto> updateDentalService(@RequestBody DentalServiceDto dentalServiceDto) {
        Long id = dentalServiceDto.getId();

        if (id == null) {
            throw new DentalServiceValidationException("ID must be provided in the request body");
        }

        DentalServiceDto updatedDentalService = dentalServiceService.updateDentalService(id, dentalServiceDto);
        return ResponseEntity.ok(updatedDentalService);
    }

    // Удалить услугу по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        dentalServiceService.deleteDentalServiceById(id);
        return ResponseEntity.noContent().build();
    }
}
