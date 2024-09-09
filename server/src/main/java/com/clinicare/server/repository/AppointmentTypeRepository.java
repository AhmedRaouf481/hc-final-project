package com.clinicare.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicare.server.domain.db.AppointmentType;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentType,Long>{

}
