package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.domain.db.Specialization;
import com.clinicare.server.exception.ResourceNotFoundException;
import com.clinicare.server.repository.DoctorRepository;
import com.clinicare.server.repository.SpecializationRepository;
import com.clinicare.server.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecializationRepository specializationRepository;

    @Override
    public List<Doctor> findAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return Optional.ofNullable(this.doctorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor")
        ));
    }

    @Override
    public Doctor saveOrUpdate(Doctor doctor) {

        return this.doctorRepository.save(doctor);
    }

    @Override
    public void delete(Long id) {
        Optional<Doctor> doctor = this.doctorRepository.findById(id);
        if (doctor.isEmpty())
            throw new ResourceNotFoundException("Doctor");

        doctorRepository.deleteById(id);
    }

    @Override
    public List<Doctor> findDoctorsBySpecialization(Long id) {
        Optional<Specialization> specialization = Optional.ofNullable(specializationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Specialization")
        ));
        return this.doctorRepository.findDoctorsBySpecialization(specialization);
    }

}
