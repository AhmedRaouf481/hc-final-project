package com.clinicare.server.service;

import com.clinicare.server.domain.db.Clinic;
import com.clinicare.server.domain.response.ClinicWithLocationProjection;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClinicService {
    List<Clinic> finaAll();

    List<ClinicWithLocationProjection> findByDoctorId(Long doctorId);

    Optional<Clinic> findById(Long id);

    Clinic saveOrUpdate(Clinic clinic);

    void delete(Long id);

    boolean removeLocFromClinic(Long clinic_id, Long loc_id);
}
