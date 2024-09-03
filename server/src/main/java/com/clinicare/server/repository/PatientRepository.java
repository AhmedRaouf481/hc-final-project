package com.clinicare.server.repository;

import com.clinicare.server.domain.db.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);
}
