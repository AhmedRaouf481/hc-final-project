package com.clinicare.server.repository;

import com.clinicare.server.domain.db.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    MedicalRecord findByPatientId(Long patientId);
    MedicalRecord findByDoctorId(Long doctorId);
}
