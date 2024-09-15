package com.clinicare.server.controller;

import com.clinicare.server.service.DoctorClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor-clinic")
@RequiredArgsConstructor
public class DoctorClinicController {

    private final DoctorClinicService doctorClinicService;

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
            Map<String,String> res = new HashMap<>();
            res.put("message", "doctor was removed successfully");
            doctorClinicService.removeDoctorFromClinic(doc_id, clinic_id);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
