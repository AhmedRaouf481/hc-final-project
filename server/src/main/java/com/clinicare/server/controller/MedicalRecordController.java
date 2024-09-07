package com.clinicare.server.controller;

import com.clinicare.server.domain.db.MedicalRecord;
import com.clinicare.server.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/m-record")
public class MedicalRecordController {
    @Autowired
    private  MedicalRecordService medicalRecordService;

    @GetMapping
    public ResponseEntity<?> getAllMedicalRecords() {
        return ResponseEntity.ok(medicalRecordService.getAllMedicalRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicalRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordById(id));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<?> getMedicalRecordByPatientId(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.findMedicalRecordByPatientId(id));
    }
    @GetMapping("/doctor/{id}")
    public ResponseEntity<?> getMedicalRecordByDoctorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.findMedicalRecordByDoctorId(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedicalRecord(@PathVariable Long id,@RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.ok(medicalRecordService.updateMedicalRecord(id,medicalRecord));
    }
    @PostMapping
    public ResponseEntity<?> saveMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.ok(medicalRecordService.saveMedicalRecord(medicalRecord));
    }
}
