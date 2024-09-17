package com.clinicare.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDTO {

    private Long id;
    private String diagnosis;
    private String treatment;
    private LocalDate createdAt;
    private PatientDTO patient;
    private DoctorDTO doctor;
    private PrescriptionDTO prescription;
    private AppointmentDTO appointment;

}
