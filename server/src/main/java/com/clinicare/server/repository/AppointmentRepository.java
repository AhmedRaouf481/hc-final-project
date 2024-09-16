package com.clinicare.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinicare.server.domain.db.Appointment;
import com.clinicare.server.domain.response.AppointmentDto;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsBySlotId(Long slotId);

    List<Appointment> findBySlotId(Long slotId);

    @Query("SELECT a FROM Appointment a WHERE a.slot.doctor.id = :doctorId")
    List<Appointment> findAppointmentsByDoctorId(@Param("doctorId") Long doctorId);

    @Query("SELECT new com.clinicare.server.domain.response.AppointmentDto( " +
    "a.slot.doctor.id, a.slot.doctor.name, " +
    "a.patient.id, a.patient.name, " +
    "a.slot.clinicLocation.clinic.id, a.slot.clinicLocation.clinic.name, " +
    "a.slot.clinicLocation.id, a.slot.clinicLocation.addressLine, a.slot.clinicLocation.city, " +
    "a.slot.id, a.slot.startTime, " +
    "a.date) " +
    "FROM Appointment a WHERE a.patient.id = :patientId")
    List<AppointmentDto> findAppointmentsByPatientId(@Param("patientId") Long patientId);
}
