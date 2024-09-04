package com.clinicare.server.repository;

import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.domain.db.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findDoctorsBySpecialization(Optional<Specialization> specialization);
}
