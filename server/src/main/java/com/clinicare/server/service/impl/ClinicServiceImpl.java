package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Clinic;
import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.domain.db.Location;
import com.clinicare.server.domain.response.ClinicWithLocationProjection;
import com.clinicare.server.exception.ResourceNotFoundException;
import com.clinicare.server.repository.ClinicRepository;
import com.clinicare.server.repository.DoctorRepository;
import com.clinicare.server.repository.LocationRepository;
import com.clinicare.server.service.ClinicService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Clinic> finaAll() {
        return clinicRepository.findAll();
    }

    @Override
    public Optional<Clinic> findById(Long id) {
        return clinicRepository.findById(id);
    }

    @Override
    @Transactional
    public Clinic saveOrUpdate(Clinic clinic) {
        if (clinic.getId() == null) {
            Clinic savedClinic = clinicRepository.save(clinic);
            for (Location location : clinic.getLocations()) {
                location.setClinic(savedClinic);
                locationRepository.save(location);

            }
            return savedClinic;
        }
        return clinicRepository.save(clinic);
    }

    @Override
    public void delete(Long id) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("clinic id not found"));
        clinicRepository.delete(clinic);
    }

    @Override
    @Transactional
    public boolean removeLocFromClinic(Long clinic_id, Long loc_id) {

        return locationRepository.findById(loc_id)
                .map(location -> {
                    Clinic clinic = location.getClinic();
                    if (clinic != null && clinic.getId().equals(clinic_id)) {
                        clinic.getLocations().remove(location);
                        clinicRepository.save(clinic);
                        return true;
                    }
                    return false;
                })
                .orElse(false);

    }

    @Override
    public List<ClinicWithLocationProjection> findByDoctorId(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor"));

        return clinicRepository.findByDoctors(doctor);
    }
}
