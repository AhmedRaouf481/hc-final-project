package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Specialization;
import com.clinicare.server.repository.SpecializationRepository;
import com.clinicare.server.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specRepo;


    @Override
    public List<Specialization> findAll() {
        return specRepo.findAll();
    }
}
