package com.clinicare.server.domain.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
        
    @ManyToOne()
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private AppointmentStatus status;

    @ManyToOne()
    @JoinColumn(name = "type_id")
    private AppointmentType type;

    @ManyToOne()
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    private Slot slot;

    @OneToOne(mappedBy = "appointment")
    @JsonIgnoreProperties("appointment")
    private MedicalRecord medicalRecord;


}
