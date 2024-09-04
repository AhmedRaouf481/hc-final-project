package com.clinicare.server.service;

import com.clinicare.server.domain.db.Specialization;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SpecializationService {
    List<Specialization> findAll();
}
