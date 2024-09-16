package com.clinicare.server.repository;

import com.clinicare.server.domain.db.Clinic;
import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.domain.db.Location;
import com.clinicare.server.domain.response.ClinicWithLocationProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    List<ClinicWithLocationProjection> findByDoctors(Doctor doctor);
}
