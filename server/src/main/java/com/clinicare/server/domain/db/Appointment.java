package com.clinicare.server.domain.db;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // add relation with clinic 

    @ManyToOne
    @JoinColumn(name = "status_id")
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    private Slot slot;


}
