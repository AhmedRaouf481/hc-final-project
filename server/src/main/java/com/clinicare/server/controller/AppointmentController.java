package com.clinicare.server.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinicare.server.domain.db.Appointment;
import com.clinicare.server.domain.db.User;
import com.clinicare.server.domain.request.ChangeStatusApptRequest;
import com.clinicare.server.domain.request.RescheduleAppointmentRequest;
import com.clinicare.server.service.AppointmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<?> addAppointment(@RequestBody Appointment entity) {

        return ResponseEntity.ok(appointmentService.addAppointment(entity));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyAppointments(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(appointmentService.getMyAppointment(user));
    }

    @GetMapping()
    public ResponseEntity<?> getAppointment(
        @RequestParam(value = "doctorId", required = false) Long doctorId,
        @RequestParam(value = "patientId", required = false) Long patientId,
        @RequestParam(value = "statusId", required = false) Long statusId,
        @RequestParam(value = "typeId", required = false) Long typeId,
        @RequestParam(value = "date", required = false) LocalDate date
 
    ) {
        return ResponseEntity.ok(appointmentService.getAllAppointment(doctorId,patientId,statusId, typeId, date));
    }

    @GetMapping("slot/{slotId}")
    public ResponseEntity<?> getAppointmentBySlotId(@PathVariable Long slotId) {
        return ResponseEntity.ok(appointmentService.getAppointmentBySlotId(slotId));
    }

    @GetMapping("types")
    public ResponseEntity<?> getAppointmentTypes() {
        return ResponseEntity.ok(appointmentService.getAppointmentTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> rescheduleAppintment(@PathVariable Long id,
            @Valid @RequestBody RescheduleAppointmentRequest appointment) {
        return ResponseEntity.ok(appointmentService.reschaduleAppointment(id, appointment));
    }

    @PutMapping("status/{id}")
    public ResponseEntity<?> changeApptStatus(@PathVariable Long id,
            @Valid @RequestBody ChangeStatusApptRequest appointment) {
        return ResponseEntity.ok(appointmentService.changeStatus(id, appointment.getStatusId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
