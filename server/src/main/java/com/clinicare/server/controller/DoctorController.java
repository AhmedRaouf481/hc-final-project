package com.clinicare.server.controller;

import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAll() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Doctor>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.findById(id));

    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Doctor doctor) {
        //TODO using DTOs
        return ResponseEntity.ok(doctorService.saveOrUpdate(doctor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Doctor doctor)
    {
        //TODO using DTOs
        Optional<Doctor> desiredDoctor = doctorService.findById(id);
        if(desiredDoctor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        doctor.setId(id);
        if(doctor.getPassword() == null)
            doctor.setPassword(desiredDoctor.get().getPassword());
        doctor.setCreatedAt(desiredDoctor.get().getCreatedAt());
        return ResponseEntity.ok(doctorService.saveOrUpdate(doctor));
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorService.delete(id);
    }

    @GetMapping("/spec/{spec_id}")
    public ResponseEntity<List<Doctor>> findBySpecialization(@PathVariable Long spec_id) {
        return ResponseEntity.ok(doctorService.findDoctorsBySpecialization(spec_id));
    }
}
