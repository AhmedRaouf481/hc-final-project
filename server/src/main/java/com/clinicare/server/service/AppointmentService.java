package com.clinicare.server.service;

import java.util.List;
import java.time.LocalDate;

import com.clinicare.server.domain.db.Appointment;
import com.clinicare.server.domain.db.AppointmentType;
import com.clinicare.server.domain.db.User;
import com.clinicare.server.domain.request.RescheduleAppointmentRequest;
import com.clinicare.server.domain.response.AppointmentDto;
import com.clinicare.server.domain.response.MyApptsResponse;

public interface AppointmentService {
    Appointment addAppointment(Appointment appointment);

    MyApptsResponse getMyAppointment(User user);

    List<AppointmentDto> getAllAppointment(Long doctorId, Long patientId, Long statusId, Long typeId, LocalDate date);

    List<AppointmentDto> getAppointmentBySlotId(Long slotId);

    List<AppointmentType> getAppointmentTypes();

    Appointment getById(Long id);

    Appointment reschaduleAppointment(Long id, RescheduleAppointmentRequest appointmentRequest);

    Appointment changeStatus(Long id, Long statusId);

    void deleteAppointment(Long id);
}
