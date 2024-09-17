package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.MedicalRecord;
import com.clinicare.server.dto.*;
import com.clinicare.server.repository.MedicalRecordRepository;
import com.clinicare.server.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;


    @Override
    public void deleteMedicalRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    @Override
    public MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecordRequest) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Medical Record not found"));
        medicalRecordRequest.setId(medicalRecord.getId());
        return medicalRecordRepository.save(medicalRecordRequest);
    }

    @Override
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecordRequest) {
        return medicalRecordRepository.save(medicalRecordRequest);
    }

    public MedicalRecordDTO getMedicalRecordById(Long id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical Record not found"));
        return convertToDTO(medicalRecord);
    }

    @Override
    public List<MedicalRecordDTO> getAllMedicalRecords() {
     List<MedicalRecord> mrs = medicalRecordRepository.findAll();
     return mrs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MedicalRecord findMedicalRecordByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    @Override
    public MedicalRecord findMedicalRecordByDoctorId(Long doctorId) {
        return medicalRecordRepository.findByDoctorId(doctorId);
    }


    private MedicalRecordDTO convertToDTO(MedicalRecord medicalRecord) {
        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setId(medicalRecord.getId());
        dto.setDiagnosis(medicalRecord.getDiagnosis());
        dto.setTreatment(medicalRecord.getTreatment());
        dto.setCreatedAt(medicalRecord.getCreatedAt());

        if (medicalRecord.getDoctor() != null) {
            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setId(medicalRecord.getDoctor().getId());
            doctorDTO.setName(medicalRecord.getDoctor().getName());
            doctorDTO.setEmail(medicalRecord.getDoctor().getEmail());
            doctorDTO.setPhone(medicalRecord.getDoctor().getPhone());
            doctorDTO.setSpecialization(medicalRecord.getDoctor().getSpecialization());
            dto.setDoctor(doctorDTO);
        }

        if (medicalRecord.getPatient() != null) {
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setId(medicalRecord.getPatient().getId());
            patientDTO.setName(medicalRecord.getPatient().getName());
            patientDTO.setEmail(medicalRecord.getPatient().getEmail());
            patientDTO.setPhone(medicalRecord.getPatient().getPhone());
            dto.setPatient(patientDTO);
        }

        if (medicalRecord.getAppointment() != null) {
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            appointmentDTO.setId(medicalRecord.getAppointment().getId());
            if (medicalRecord.getAppointment().getSlot() != null) {
                SlotDTO slotDTO = new SlotDTO();
                slotDTO.setId(medicalRecord.getAppointment().getSlot().getId());
                slotDTO.setSlot_time(medicalRecord.getAppointment().getSlot().getStartTime());
                appointmentDTO.setSlot(slotDTO);
            }
            if(medicalRecord.getAppointment().getSlot().getClinicLocation().getClinic() != null)
            {
                ClinicDTO clinicDto = new ClinicDTO();
                clinicDto.setId(medicalRecord.getAppointment().getSlot().getClinicLocation().getClinic().getId());
                clinicDto.setLocation(medicalRecord.getAppointment().getSlot().getClinicLocation());
                clinicDto.setName(medicalRecord.getAppointment().getSlot().getClinicLocation().getClinic().getName());
                appointmentDTO.setClinic(clinicDto);
            }
            appointmentDTO.setType(medicalRecord.getAppointment().getType());
            dto.setAppointment(appointmentDTO);
        }

        if(medicalRecord.getPrescription() != null)
        {
            PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
            prescriptionDTO.setId(medicalRecord.getPrescription().getId());
            prescriptionDTO.setMedication(medicalRecord.getPrescription().getMedication());
            prescriptionDTO.setInstructions(medicalRecord.getPrescription().getInstructions());
            prescriptionDTO.setMedication(medicalRecord.getPrescription().getMedication());
            dto.setPrescription(prescriptionDTO);
        }
        return dto;
    }
}
