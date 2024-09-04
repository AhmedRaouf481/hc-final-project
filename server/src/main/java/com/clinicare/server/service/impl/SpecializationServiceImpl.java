package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Specialization;
import com.clinicare.server.repository.SpecializationRepository;
import com.clinicare.server.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationRepository specRepo;


    @Override
    public List<Specialization> findAll() {
        return specRepo.findAll();
    }
}
