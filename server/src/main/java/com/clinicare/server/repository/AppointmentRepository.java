package com.clinicare.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicare.server.domain.db.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

}
