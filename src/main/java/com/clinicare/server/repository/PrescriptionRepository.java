package com.clinicare.server.repository;

import com.clinicare.server.domain.db.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
//    Prescription findPrescriptionByRecordId(Long recordId);
}
