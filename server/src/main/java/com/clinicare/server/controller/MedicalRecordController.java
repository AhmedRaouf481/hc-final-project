package com.clinicare.server.controller;

import com.clinicare.server.domain.db.MedicalRecord;
import com.clinicare.server.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/m-record")
public class MedicalRecordController {
    @Autowired
    private  MedicalRecordService medicalRecordService;

    @GetMapping
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }
    @GetMapping("/patient/{id}")
    public MedicalRecord getMedicalRecordByPatientId(Long patientId) {
        return medicalRecordService.findMedicalRecordByPatientId(patientId);
    }
    @GetMapping("/doctor/{id}")
    public MedicalRecord getMedicalRecordByDoctorId(Long doctorId) {
        return medicalRecordService.findMedicalRecordByDoctorId(doctorId);
    }
    @DeleteMapping
    public void deleteMedicalRecord(Long id) {
        medicalRecordService.deleteMedicalRecord(id);
    }
    @PutMapping
    public MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecord) {
        return medicalRecordService.updateMedicalRecord(id, medicalRecord);
    }
}
