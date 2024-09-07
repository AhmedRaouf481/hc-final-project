package com.clinicare.server.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinicare.server.domain.db.Appointment;
import com.clinicare.server.exception.ResourceNotFoundException;
import com.clinicare.server.repository.DoctorRepository;
import com.clinicare.server.repository.AppointmentRepository;
import com.clinicare.server.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment"));
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
         // TODO Auto-generated method stub
         throw new UnsupportedOperationException("Unimplemented method 'updateAppointment'");
        // Appointment appointment = appointmentRepository.findById(id)
        //         .orElseThrow(() -> new ResourceNotFoundException("Appointment"));
        // appointment.setStartTime(appointmentDetails.g());
        // appointment.setEndTime(appointmentDetails.getEndTime());
        // return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment"));
        appointmentRepository.delete(appointment);
    }

    @Override
    public List<Appointment> getByDoctorId(Long doctorId) {
        doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor"));
        return appointmentRepository.findByDoctorId(doctorId);
    }

}
