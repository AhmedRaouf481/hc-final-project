package com.clinicare.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicare.server.domain.db.Slot;
import com.clinicare.server.domain.request.SlotRequest;
import com.clinicare.server.service.SlotService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api/slot")
@RequiredArgsConstructor
public class SlotController {
    private final SlotService slotService;

    @PostMapping
    public ResponseEntity<?> addSlot(@RequestBody SlotRequest entity) {
        return ResponseEntity.ok(slotService.addSlot(entity));
    }
    
}
