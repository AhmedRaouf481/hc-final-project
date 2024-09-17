package com.clinicare.server.service;

import com.clinicare.server.domain.db.Doctor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public interface DoctorService {
    List<Doctor> findAll();
    Optional<Doctor> findById(Long id);
    Doctor saveOrUpdate(Doctor doctor);
    void delete(Long id);
    List<Doctor> findDoctorsBySpecialization(Long id);
}
