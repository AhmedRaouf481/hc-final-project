package com.clinicare.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinicare.server.domain.db.Slot;
import com.clinicare.server.domain.db.enums.DayOfWeek;
import com.clinicare.server.service.SlotService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/api/slot")
@RequiredArgsConstructor
public class SlotController {
    private final SlotService slotService;

    @PostMapping
    public ResponseEntity<?> addSlot(@RequestBody Slot entity) {
        return ResponseEntity.ok(slotService.addSlot(entity));
    }

    @GetMapping()
    public ResponseEntity<?> getSlots(
            @RequestParam(value = "doctorId", required = false) Long doctorId,
            @RequestParam(value = "locationId", required = false) Long locationId,
            @RequestParam(value = "clinicId", required = false) Long clinicId,
            @RequestParam(value = "weekDay", required = false) DayOfWeek weekDay) {

        return ResponseEntity.ok(slotService.getAllSlot(doctorId, locationId, clinicId, weekDay));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSlotById(@PathVariable Long id) {
        return ResponseEntity.ok(slotService.getById(id));
    }

    @GetMapping("doctor/{doctorId}")
    public ResponseEntity<?> getSlotByDortorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(slotService.getByDoctorId(doctorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSlot(@PathVariable Long id, @RequestBody Slot slot) {
        return ResponseEntity.ok(slotService.updateSlot(id, slot));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlot(@PathVariable Long id) {
        slotService.deleteSlot(id);
        return ResponseEntity.noContent().build();
    }

}
