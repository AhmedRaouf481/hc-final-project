package com.clinicare.server.service;

import com.clinicare.server.domain.db.MedicalRecord;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordService {
    void deleteMedicalRecord(Long id);

    MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecord);

    MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

    Optional<MedicalRecord> getMedicalRecordById(Long id);

    List<MedicalRecord> getAllMedicalRecords();

    MedicalRecord findMedicalRecordByPatientId(Long patientId);

    MedicalRecord findMedicalRecordByDoctorId(Long doctorId);
}
