package com.clinicare.server.controller;

import com.clinicare.server.domain.db.Clinic;
import com.clinicare.server.domain.response.ClinicWithLocationProjection;
import com.clinicare.server.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clinics")
@RequiredArgsConstructor
public class ClinicController {

    private final ClinicService clinicService;


    @GetMapping
    public ResponseEntity<List<Clinic>> getAll() {
        return ResponseEntity.ok(clinicService.finaAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clinic>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clinicService.findById(id));
    }

    @GetMapping("doctor/{doctorId}")
    public ResponseEntity<List<ClinicWithLocationProjection>> getByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(clinicService.findByDoctorId(doctorId));
    }

    @PostMapping
    public ResponseEntity<Clinic> save(@RequestBody Clinic clinic) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clinicService.saveOrUpdate(clinic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Clinic requestClinic) {
        Optional<Clinic> clinic = clinicService.findById(id);
        if (clinic.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("clinic not found");
        }
        requestClinic.setId(clinic.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(clinicService.saveOrUpdate(requestClinic));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Clinic> clinic = clinicService.findById(id);

        if (clinic.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("clinic not found");
        }
        clinicService.delete(clinic.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body("clinic Deleted Successfully");
    }

    @DeleteMapping("/location/{clinic_id}/{loc_id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long clinic_id, @PathVariable Long loc_id) {
        boolean deleted = clinicService.removeLocFromClinic(clinic_id, loc_id);
        if (deleted) {
            return ResponseEntity.ok("Location deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location wasn't found or doesn't belong to the specified clinic");
        }
    }


}
