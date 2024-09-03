package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Patient;
import com.clinicare.server.repository.PatientRepository;
import com.clinicare.server.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;


    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }


    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient findPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }
}