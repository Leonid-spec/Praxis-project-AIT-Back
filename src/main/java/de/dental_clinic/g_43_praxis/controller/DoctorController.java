package de.dental_clinic.g_43_praxis.controller;

import de.dental_clinic.g_43_praxis.domain.dto.DoctorDto;
import de.dental_clinic.g_43_praxis.service.interfaces.DoctorService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Transactional
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Получить список активных врачей
    @GetMapping("/doctors/active")
    public ResponseEntity<List<DoctorDto>> getActiveDoctors() {
        List<DoctorDto> activeDoctors = doctorService.getActiveDoctors();
        return ResponseEntity.ok(activeDoctors);
    }

    // Получить врача по ID
    @GetMapping("/doctor/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id) {
        DoctorDto doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    // Получить всех врачей (для админа)
    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        List<DoctorDto> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    // Создать нового врача
    @PostMapping("/doctor")
    public ResponseEntity<DoctorDto> addDoctor(@RequestBody DoctorDto doctorDto) {
        DoctorDto newDoctor = doctorService.addDoctor(doctorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDoctor);
    }

    // Обновить данные врача
    @PutMapping("/doctor/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long id, @RequestBody DoctorDto doctorDto) {
        DoctorDto updatedDoctor = doctorService.updateDoctor(id, doctorDto);
        return ResponseEntity.ok(updatedDoctor);
    }

    // Удалить врача по ID
//    @DeleteMapping("/doctor/{id}")
//    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
//        doctorService.deleteDoctorById(id);
//        return ResponseEntity.noContent().build();
//    }
}
