package com.clinicare.server.controller;


import com.clinicare.server.domain.db.Prescription;
import com.clinicare.server.service.PrescriptionService;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Prescription>> getById(@PathVariable Long id) {
        Optional<Prescription> prescription = prescriptionService.findByRecordId(id);
        if(prescription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prescriptionService.findByRecordId(id));
    }

    @PostMapping
    public ResponseEntity<?> savePrescription(@RequestBody Prescription prescription) {
            Prescription savedPrescription = new Prescription();
            try {
                savedPrescription = prescriptionService.savePrescription(prescription);
            } catch (Exception e) {
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
            }
        return  ResponseEntity.status(HttpStatus.OK).body(savedPrescription);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable Long id, @RequestBody Prescription prescription) {
//        return prescriptionService.updatePrescription(id, prescription);
        return new ResponseEntity<Prescription>(prescriptionService.updatePrescription(id, prescription), HttpStatus.OK);

    }


//    @GetMapping("/prescription/{id}")
//    public Prescription getPrescriptionById(Long id) {
//        return prescriptionService.getPrescriptionById(id).orElseThrow();
//    }
//    @GetMapping("/{id}")
//    public Optional<Prescription> findByRecordId(Long recordId) {
//        return prescriptionService.findByRecordId(recordId);
//    }

    @DeleteMapping("/{id}")
    public void deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
    }
}
