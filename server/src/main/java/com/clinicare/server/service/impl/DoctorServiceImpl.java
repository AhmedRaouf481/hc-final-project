package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.domain.db.Specialization;
import com.clinicare.server.repository.DoctorRepository;
import com.clinicare.server.repository.SpecializationRepository;
import com.clinicare.server.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public List<Doctor> findAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return this.doctorRepository.findById(id);
    }

    @Override
    public Doctor saveOrUpdate(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }

    @Override
    public void delete(Long id) {
        Optional<Doctor> doctor = this.doctorRepository.findById(id);
        if (doctor.isEmpty())
            throw new IllegalArgumentException("this user doesn't exist");

        doctorRepository.deleteById(id);
    }

    @Override
    public List<Doctor> findDoctorsBySpecialization(Long id) {
        Optional<Specialization> specialization = Optional.ofNullable(specializationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("not found id of specialization")
        ));
        return this.doctorRepository.findDoctorsBySpecialization(specialization);
    }

}
