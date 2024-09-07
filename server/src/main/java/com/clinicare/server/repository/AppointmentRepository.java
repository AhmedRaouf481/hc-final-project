package com.clinicare.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicare.server.domain.db.Appointment;
import com.clinicare.server.domain.db.Patient;
import com.clinicare.server.domain.db.Slot;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByPatientId(Patient patient);

    List<Appointment> findBySlotId(Slot slot);
}
