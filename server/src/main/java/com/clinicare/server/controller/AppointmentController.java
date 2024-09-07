package com.clinicare.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicare.server.domain.db.Appointment;
import com.clinicare.server.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {
  private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<?> addAppointment(@RequestBody Appointment entity) {
        appointmentService.addAppointment(entity);
        return ResponseEntity.ok("Appointment added successfully");
    }

    @GetMapping()
    public ResponseEntity<?> getAppointment() {
        return ResponseEntity.ok(appointmentService.getAllAppointment());
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getById(id));
    }

    @GetMapping("doctor/{doctorId}")
    public ResponseEntity<?> getAppointmentByDortorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getByDoctorId(doctorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id,appointment));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
    
}
