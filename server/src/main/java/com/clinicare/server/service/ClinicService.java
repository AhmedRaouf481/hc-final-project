package com.clinicare.server.service;


import com.clinicare.server.domain.db.Clinic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClinicService {
    List<Clinic> finaAll();
    Optional<Clinic> findById(Long id);
    Clinic saveOrUpdate(Clinic clinic);
    void delete(Long id);
    boolean removeLocFromClinic(Long clinic_id, Long loc_id);
}
