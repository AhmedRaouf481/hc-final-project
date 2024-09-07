package com.clinicare.server.service;

import java.util.List;

import com.clinicare.server.domain.db.Appointment;

public interface AppointmentService {
    Appointment addAppointment(Appointment appointment);

    List<Appointment> getAllAppointment();

    Appointment getById(Long id);

    List<Appointment> getByDoctorId(Long doctorId);

    Appointment updateAppointment(Long id, Appointment appointmentDetails);

    void deleteAppointment(Long id);
}
