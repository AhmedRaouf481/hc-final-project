package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Clinic;
import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.domain.db.DoctorClinic;
import com.clinicare.server.repository.ClinicRepository;
import com.clinicare.server.repository.DoctorClinicRepository;
import com.clinicare.server.repository.DoctorRepository;
import com.clinicare.server.service.DoctorClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DoctorClinicServiceImpl implements DoctorClinicService {

    @Autowired
    private DoctorClinicRepository doctorClinicRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public DoctorClinic addDoctorToClinic(Long doc_id, Long clinic_id) {
        Doctor doctor = doctorRepository.findById(doc_id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + doc_id));

        Clinic clinic = clinicRepository.findById(clinic_id)
                .orElseThrow(() -> new IllegalArgumentException("Clinic not found with id: " + clinic_id));

        DoctorClinic doctorClinic = new DoctorClinic();
        doctorClinic.setClinic(clinic);
        doctorClinic.setDoctor(doctor);
        return doctorClinicRepository.save(doctorClinic);
    }

    @Override
    public void removeDoctorFromClinic(Long doc_id, Long clinic_id) {
        Doctor doctor = doctorRepository.findById(doc_id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + doc_id));

        Clinic clinic = clinicRepository.findById(clinic_id)
                .orElseThrow(() -> new IllegalArgumentException("Clinic not found with id: " + clinic_id));

        DoctorClinic doctorClinic = doctorClinicRepository.findByDoctorAndClinic(doctor, clinic)
                .orElseThrow(() -> new IllegalArgumentException("DoctorClinic relationship not found"));

        doctorClinicRepository.delete(doctorClinic);
    }


}
