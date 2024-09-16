package com.clinicare.server.service;

import com.clinicare.server.domain.db.MedicalRecord;
import com.clinicare.server.dto.MedicalRecordDTO;

import java.util.List;

public interface MedicalRecordService {
    void deleteMedicalRecord(Long id);

    MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecord);

    MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecordDTO getMedicalRecordById(Long id);

    List<MedicalRecordDTO> getAllMedicalRecords();

    MedicalRecord findMedicalRecordByPatientId(Long patientId);

    MedicalRecord findMedicalRecordByDoctorId(Long doctorId);
}
