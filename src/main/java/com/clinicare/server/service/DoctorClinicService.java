package com.clinicare.server.service;

import com.clinicare.server.domain.db.DoctorClinic;
import org.springframework.stereotype.Service;


@Service
public interface DoctorClinicService {
    DoctorClinic addDoctorToClinic(Long doc_id, Long clinic_id);
    void removeDoctorFromClinic(Long doc_id, Long clinic_id);
}
