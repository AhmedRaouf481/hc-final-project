package com.clinicare.server.service;

import com.clinicare.server.domain.db.Patient;
import com.clinicare.server.domain.request.PatientRequest;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    List<Patient> getAllPatients();

    Optional<Patient> getPatientById(Long id);

    Patient savePatient(PatientRequest patient);

    void deletePatient(Long id);


    Patient updatePatient(Long id, PatientRequest patientRequest);

    Patient findPatientByEmail(String email);

    void saveDoctorAsPatient(Long id);


}