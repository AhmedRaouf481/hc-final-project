package com.clinicare.server.controller;

import com.clinicare.server.domain.db.MedicalRecord;
import com.clinicare.server.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/m-record")
public class MedicalRecordController {
    @Autowired
    private  MedicalRecordService medicalRecordService;

    @GetMapping
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }

    @GetMapping("/{id}")
    public Optional<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        return medicalRecordService.getMedicalRecordById(id);
    }

    @GetMapping("/patient/{id}")
    public MedicalRecord getMedicalRecordByPatientId(@PathVariable Long id) {
        return medicalRecordService.findMedicalRecordByPatientId(id);
    }
    @GetMapping("/doctor/{id}")
    public MedicalRecord getMedicalRecordByDoctorId(@PathVariable Long id) {
        return medicalRecordService.findMedicalRecordByDoctorId(id);
    }
    @DeleteMapping("/{id}")
    public void deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
    }
    @PutMapping("/{id}")
    public MedicalRecord updateMedicalRecord(@PathVariable Long id,@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.updateMedicalRecord(id, medicalRecord);
    }
    @PostMapping
    public MedicalRecord saveMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.saveMedicalRecord(medicalRecord);
    }
}
