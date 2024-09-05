package com.clinicare.server.service.impl;


import com.clinicare.server.domain.db.Prescription;
import com.clinicare.server.repository.PrescriptionRepository;
import com.clinicare.server.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;


    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }


    @Override
    public Optional<Prescription> findByRecordId(Long recordId) {
        return prescriptionRepository.findById(recordId);
    }

    @Override
    public Prescription savePrescription(Prescription prescription) {
//        return prescription;
        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription updatePrescription(Long id, Prescription prescription) {
        Prescription prescription1 = prescriptionRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Prescription not found"));
        prescription.setId(prescription1.getId());
        return prescriptionRepository.save(prescription);
    }

    @Override
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }




//    @Override
//    public Optional<Prescription> getPrescriptionById(Long id) {
//        return prescriptionRepository.findById(id);
//    }



//    @Override
//    public Prescription findPrescriptionByRecordId(Long recordId) {
//        return prescriptionRepository.findPrescriptionByRecordId(recordId);
//    }
}
