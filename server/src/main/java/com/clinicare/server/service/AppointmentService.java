package com.clinicare.server.service;

import java.util.List;

import com.clinicare.server.domain.db.Appointment;
import com.clinicare.server.domain.db.AppointmentType;
import com.clinicare.server.domain.request.RescheduleAppointmentRequest;

public interface AppointmentService {
    Appointment addAppointment(Appointment appointment);

    List<Appointment> getAllAppointment();

    List<Appointment> getAppointmentBySlotId(Long slotId);

    List<AppointmentType> getAppointmentTypes();

    Appointment getById(Long id);

    Appointment reschaduleAppointment(Long id, RescheduleAppointmentRequest appointmentRequest);

    Appointment changeStatus(Long id, Long statusId);

    void deleteAppointment(Long id);
}
