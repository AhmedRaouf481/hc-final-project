package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.MedicalRecord;
import com.clinicare.server.domain.response.MedicalRecordResponse;
import com.clinicare.server.repository.MedicalRecordRepository;
import com.clinicare.server.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

//    private  final MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private  MedicalRecordRepository medicalRecordRepository;
    private MedicalRecord medicalRecord;


//    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
//        this.medicalRecordRepository = medicalRecordRepository;
//    }

    @Override
    public void deleteMedicalRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    @Override
    public MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecordRequest) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Medical Record not found"));
        medicalRecordRequest.setId(medicalRecord.getId());
        return medicalRecordRepository.save(medicalRecordRequest);
    }

    @Override
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecordRequest) {
        return medicalRecordRepository.save(medicalRecordRequest);
    }

    @Override
    public Optional<MedicalRecord> getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public MedicalRecord findMedicalRecordByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    @Override
    public MedicalRecord findMedicalRecordByDoctorId(Long doctorId) {
        return medicalRecordRepository.findByDoctorId(doctorId);
    }
    public MedicalRecordResponse mapToMedicalRecordResponse(Optional<MedicalRecord> medicalRecord) {
        return new MedicalRecordResponse(
                medicalRecord.get().getId(),
                medicalRecord.get().getDiagnosis(),
                medicalRecord.get().getTreatment(),
                medicalRecord.get().getCreatedAt(),
                medicalRecord.get().getDoctor().getName(),
                medicalRecord.get().getPatient().getId(),
                medicalRecord.get().getAppointment().getId()
        );
    }
}
