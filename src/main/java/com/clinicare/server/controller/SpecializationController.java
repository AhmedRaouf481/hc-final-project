package com.clinicare.server.controller;

import com.clinicare.server.domain.db.Specialization;
import com.clinicare.server.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/specs")
@RequiredArgsConstructor
public class SpecializationController {

    private final SpecializationService specializationService;

    @GetMapping
    public ResponseEntity<List<Specialization>> getAll()
    {
        return ResponseEntity.ok(specializationService.findAll());
    }
}
