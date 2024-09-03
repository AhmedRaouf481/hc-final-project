package com.clinicare.server.service;

import com.clinicare.server.domain.db.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    List<Patient> getAllPatients();

    Optional<Patient> getPatientById(Long id);

    Patient savePatient(Patient patient);

    void deletePatient(Long id);

    Patient findPatientByEmail(String email);
}