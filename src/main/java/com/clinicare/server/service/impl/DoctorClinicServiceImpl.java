package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Clinic;
import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.domain.db.DoctorClinic;
import com.clinicare.server.exception.ResourceNotFoundException;
import com.clinicare.server.repository.ClinicRepository;
import com.clinicare.server.repository.DoctorClinicRepository;
import com.clinicare.server.repository.DoctorRepository;
import com.clinicare.server.service.DoctorClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DoctorClinicServiceImpl implements DoctorClinicService {

    private final DoctorClinicRepository doctorClinicRepository;

    private final DoctorRepository doctorRepository;

    private final ClinicRepository clinicRepository;

    @Override
    public DoctorClinic addDoctorToClinic(Long doc_id, Long clinic_id) {
        Doctor doctor = doctorRepository.findById(doc_id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor"));

        Clinic clinic = clinicRepository.findById(clinic_id)
                .orElseThrow(() -> new ResourceNotFoundException("Clinic"));

        DoctorClinic doctorClinic = new DoctorClinic();
        doctorClinic.setClinic(clinic);
        doctorClinic.setDoctor(doctor);
        return doctorClinicRepository.save(doctorClinic);
    }

    @Override
    public void removeDoctorFromClinic(Long doc_id, Long clinic_id) {
        Doctor doctor = doctorRepository.findById(doc_id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor"));

        Clinic clinic = clinicRepository.findById(clinic_id)
                .orElseThrow(() -> new ResourceNotFoundException("Clinic"));

        DoctorClinic doctorClinic = doctorClinicRepository.findByDoctorAndClinic(doctor, clinic)
                .orElseThrow(() -> new ResourceNotFoundException("DoctorClinic"));

        doctorClinicRepository.delete(doctorClinic);
    }


}
