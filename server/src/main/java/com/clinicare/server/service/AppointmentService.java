package com.clinicare.server.service;

import java.util.List;

import com.clinicare.server.domain.db.Appointment;

public interface AppointmentService {
    Appointment addAppointment(Appointment appointment);

    List<Appointment> getAllAppointment();

    Appointment getById(Long id);

    Appointment reschaduleAppointment(Long id, Long slotId);

    Appointment changeStatus(Long id, Long statusId);

    void deleteAppointment(Long id);
}
