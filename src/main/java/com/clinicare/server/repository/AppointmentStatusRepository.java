package com.clinicare.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicare.server.domain.db.AppointmentStatus;

public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus,Long>{

}
