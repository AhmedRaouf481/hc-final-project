package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Patient;
import com.clinicare.server.domain.request.PatientRequest;
import com.clinicare.server.exception.ValidationException;
import com.clinicare.server.repository.PatientRepository;
import com.clinicare.server.service.PatientService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        validatePatientId(id);
        return patientRepository.findById(id);
    }

    @Override
    public Patient savePatient(PatientRequest patient) {

        Patient newPatient = new Patient();
        newPatient.setEmail(patient.email());
        newPatient.setPassword(patient.password());
        newPatient.setUsername(patient.username());
        newPatient.setName(patient.name());
        newPatient.setPhone(patient.phone());

        return patientRepository.save(newPatient);
    }

    @Override
    public void deletePatient(Long id) {
        validatePatientId(id);
        patientRepository.deleteById(id);
    }

    @Override
    public Patient findPatientByEmail(String email) {
        validatePatientEmail(email);
        return patientRepository.findByEmail(email);
    }


    // Validation methods
    private void validatePatientId(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("Invalid Patient ID: " + id);
        }
    }

    private void validatePatientEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Patient email cannot be null or empty");
        }
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new ValidationException("Invalid email format: " + email);
        }
    }
}