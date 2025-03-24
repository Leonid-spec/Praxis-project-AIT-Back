package de.dental_clinic.g_43_praxis.controller;

import de.dental_clinic.g_43_praxis.domain.dto.DentalServiceDto;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceValidationException;
import de.dental_clinic.g_43_praxis.service.interfaces.DentalServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class DentalServiceController {

    private final DentalServiceService dentalServiceService;

    public DentalServiceController(DentalServiceService dentalServiceService) {
        this.dentalServiceService = dentalServiceService;
    }

    // Получить список активных услуг
    @GetMapping("/active")
    public ResponseEntity<List<DentalServiceDto>> getActiveDentalServices() {
        List<DentalServiceDto> activeDentalServices = dentalServiceService.getActiveDentalServices();
        return ResponseEntity.ok(activeDentalServices);
    }

    // Получить услугу по ID
    @GetMapping("/{id}")
    public ResponseEntity<DentalServiceDto> getDentalServiceById(@PathVariable Long id) {
        DentalServiceDto dentalService = dentalServiceService.getDentalServiceById(id);
        return ResponseEntity.ok(dentalService);
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
