package com.clinicare.server.controller;

import com.clinicare.server.service.DoctorClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor-clinic")
public class DoctorClinicController {

    @Autowired
    private DoctorClinicService doctorClinicService;

    @PostMapping("/{doc_id}/{clinic_id}")
    public ResponseEntity<?> addDoctorToClinic(@PathVariable Long doc_id, @PathVariable Long clinic_id) {
        try {
            return ResponseEntity.ok(doctorClinicService.addDoctorToClinic(doc_id, clinic_id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{doc_id}/{clinic_id}")
    public ResponseEntity<?> removeDoctorFromClinic(@PathVariable Long doc_id, @PathVariable Long clinic_id) {
        try {
            doctorClinicService.removeDoctorFromClinic(doc_id, clinic_id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("doctor was removed from the clinic successfully ");
    }
}
