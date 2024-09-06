package com.clinicare.server.repository;

import com.clinicare.server.domain.db.Clinic;
import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.domain.db.DoctorClinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorClinicRepository extends JpaRepository<DoctorClinic, Long> {
    Optional<DoctorClinic> findByDoctorAndClinic(Doctor doctor, Clinic clinic);
}
