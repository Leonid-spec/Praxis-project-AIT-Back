package de.dental_clinic.g_43_praxis.controller;


import de.dental_clinic.g_43_praxis.domain.dto.AppointmentDto;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.AppointmentNotFoundException;
import de.dental_clinic.g_43_praxis.service.interfaces.AppointmentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Transactional
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Создать Аппоинтмент
    @PostMapping("/appointment")
    public ResponseEntity<AppointmentDto> createAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
//        System.out.println("Received Appointment: " + appointmentDto);
        AppointmentDto createdAppointment = appointmentService.createAppointment(appointmentDto);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    // Показать все Аппоинтменты (для админа)
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        List<AppointmentDto> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    // Показать Аппоинтмент по ID
    @GetMapping("/appointment/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(appointmentDto -> new ResponseEntity<>(appointmentDto, HttpStatus.OK))
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));
    }

    // Обновить данные Аппоинтмента
    @PutMapping("/appointment/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDto appointmentDto) {
        AppointmentDto updatedAppointment = appointmentService.updateAppointment(id, appointmentDto);
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

//    @PutMapping("/{id}/status")
//    public ResponseEntity<String> changeAppointmentStatus(
//            @PathVariable Long id,
//            @RequestBody Map<String, Boolean> request) {
//
//        // Проверяем, есть ли ключ isActive в запросе
//        if (!request.containsKey("isActive")) {
//            return ResponseEntity.badRequest().body("Ошибка: Параметр 'isActive' обязателен.");
//        }
//
//        boolean isActive = request.get("isActive");
//        appointmentService.changeAppointmentStatus(id, isActive);
//        return ResponseEntity.ok("Статус обновлен: " + isActive);
//    }
//
//    @GetMapping("/active")
//    public ResponseEntity<List<AppointmentDto>> getActiveAppointments() {
//        return ResponseEntity.ok(appointmentService.getActiveAppointments());
//    }

    // Удалить Аппоинтмент
    @DeleteMapping("/appointment/{id}")
    public ResponseEntity<AppointmentDto> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
