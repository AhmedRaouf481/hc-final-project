package com.clinicare.server.repository;

import com.clinicare.server.domain.db.Clinic;
import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.domain.response.ClinicProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    List<ClinicProjection> findByDoctors(Doctor doctor);
}
