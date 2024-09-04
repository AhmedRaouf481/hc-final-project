package com.clinicare.server.controller;


import com.clinicare.server.domain.db.Prescription;
import com.clinicare.server.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }
    @GetMapping("/{id}")
    public Prescription getPrescriptionByRecordId(Long recordId) {
        return prescriptionService.findPrescriptionByRecordId(recordId);
    }
    @DeleteMapping
    public void deletePrescription(Long id) {
        prescriptionService.deletePrescription(id);
    }
    @PutMapping("{id}")
    public Prescription updatePrescription(Long id, Prescription prescription) {
        return prescriptionService.updatePrescription(id, prescription);
    }
    @PutMapping("{/s/id}")
    public Prescription savePrescription(Prescription prescription) {
        return prescriptionService.savePrescription(prescription);
    }
    @GetMapping("/p/{id}")
    public Prescription getPrescriptionById(Long id) {
        return prescriptionService.getPrescriptionById(id).orElseThrow();
    }
    @GetMapping("/r/{id}")
    public Prescription findByRecordId(Long recordId) {
        return prescriptionService.findByRecordId(recordId);
    }
}
