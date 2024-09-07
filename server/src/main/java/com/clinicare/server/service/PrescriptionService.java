package com.clinicare.server.service;

import com.clinicare.server.domain.db.Prescription;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {
    Optional<Prescription> findByRecordId(Long recordId);

    void deletePrescription(Long id);

    Prescription updatePrescription(Long id, Prescription prescription);

    Prescription savePrescription(Prescription prescription);

//    Optional<Prescription> getPrescriptionById(Long id);

    List<Prescription> getAllPrescriptions();

//    Prescription findPrescriptionByRecordId(Long recordId);
}
