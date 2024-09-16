package com.clinicare.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinicare.server.domain.db.Appointment;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsBySlotId(Long slotId);

    List<Appointment> findBySlotId(Long slotId);

    @Query("SELECT a FROM Appointment a WHERE a.slot.doctor.id = :doctorId")
    List<Appointment> findAppointmentsByDoctorId(@Param("doctorId") Long doctorId);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId")
    List<Appointment> findAppointmentsByPatientId(@Param("patientId") Long patientId);
}
