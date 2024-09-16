package com.clinicare.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import java.time.*;

import org.springframework.stereotype.Service;

import com.clinicare.server.domain.db.Appointment;
import com.clinicare.server.domain.db.AppointmentStatus;
import com.clinicare.server.domain.db.AppointmentType;
import com.clinicare.server.domain.db.Slot;
import com.clinicare.server.domain.db.User;
import com.clinicare.server.domain.request.RescheduleAppointmentRequest;
import com.clinicare.server.domain.response.AppointmentDto;
import com.clinicare.server.domain.response.DtoMapper;
import com.clinicare.server.domain.response.MyApptsResponse;
import com.clinicare.server.exception.ResourceNotFoundException;
import com.clinicare.server.exception.ValidationException;
import com.clinicare.server.repository.PatientRepository;
import com.clinicare.server.repository.SlotRepository;
import com.clinicare.server.repository.AppointmentRepository;
import com.clinicare.server.repository.AppointmentStatusRepository;
import com.clinicare.server.repository.AppointmentTypeRepository;
import com.clinicare.server.service.AppointmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final SlotRepository slotRepository;
    private final AppointmentStatusRepository appointmentStatusRepository;
    private final AppointmentTypeRepository appointmentTypeRepository;
    private final DtoMapper dtoMapper;

    @Override
    @Transactional
    public Appointment addAppointment(Appointment appointment) {
        Slot slot = validateSlot(appointment.getSlot().getId(), appointment.getDate());

        AppointmentType type = appointmentTypeRepository.findById(appointment.getType().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment Type not found"));

        if (!patientRepository.existsById(appointment.getPatient().getId())) {
            throw new ResourceNotFoundException("Patient");
        }

        // set status to scheduled
        AppointmentStatus status = appointmentStatusRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment Status not found"));

        appointment.setSlot(slot);
        appointment.setStatus(status);
        appointment.setType(type);
        return appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public MyApptsResponse getMyAppointment(User user) {
        List<AppointmentDto> doctorAppointments = new ArrayList<>();
        List<AppointmentDto> patientAppointments = new ArrayList<>();

        // Check if the user has the 'doctor' role and fetch doctor appointments
        if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("DOCTOR"))) {
            doctorAppointments = appointmentRepository.findAppointmentsByDoctorId(user.getId()).stream()
                    .map(dtoMapper::mapToAppointmentDto)
                    .collect(Collectors.toList());
        }

        // Check if the user has the 'patient' role and fetch patient appointments
        if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("PATIENT"))) {
            patientAppointments = appointmentRepository.findAppointmentsByPatientId(user.getId()).stream()
                    .map(dtoMapper::mapToAppointmentDto)
                    .collect(Collectors.toList());
        }

        return MyApptsResponse.builder()
                .doctor(doctorAppointments)
                .patient(patientAppointments)
                .build();
    }

    @Override
    @Transactional
    public List<AppointmentDto> getAllAppointment(Long doctorId, Long patientId, Long statusId, Long typeId,
            LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .filter(appt -> doctorId == null ||
                        appt.getSlot().getDoctor().getId().equals(doctorId))

                .filter(appt -> patientId == null ||
                        appt.getPatient().getId().equals(patientId))

                .filter(appt -> statusId == null ||
                        appt.getStatus().getId().equals(statusId))

                .filter(appt -> appt.getStatus().getId() != 3)

                .filter(appt -> typeId == null || appt.getType().getId().equals(typeId))

                .filter(appt -> date == null || appt.getDate().equals(date))

                .map(dtoMapper::mapToAppointmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment"));
    }

    @Override
    public Appointment reschaduleAppointment(Long id, RescheduleAppointmentRequest appointmentRequest) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment"));
        Slot slot = validateSlot(appointmentRequest.getSlotId(), appointment.getDate());
        appointment.setSlot(slot);
        return appointmentRepository.save(appointment);

    }

    @Override
    public Appointment changeStatus(Long id, Long statusId) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment"));

        AppointmentStatus status = appointmentStatusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment Status not found"));

        appointment.setStatus(status);
        return appointmentRepository.save(appointment);

    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment"));
        appointmentRepository.delete(appointment);
    }

    // Check if the slot is available.
    private Slot validateSlot(Long slotId, LocalDate date) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new ResourceNotFoundException("Slot ah" + slotId));
        List<Appointment> existingAppointments = appointmentRepository.findBySlotId(slot.getId());

        if (existingAppointments.isEmpty()) {
            return slot;
        }
        for (Appointment appointment : existingAppointments) {
            if (appointment.getStatus().getId() != 3 && appointment.getDate().equals(date)) {
                throw new ValidationException("Slot with ID " + slot.getId() + " is already booked");
            }
        }

        return slot;

    }

    @Override
    public List<AppointmentDto> getAppointmentBySlotId(Long slotId) {
        List<Appointment> appts = appointmentRepository.findBySlotId(slotId);
        return appts.stream()
                .filter(appt -> appt.getStatus().getId() != 3) 
                .map(dtoMapper::mapToAppointmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentType> getAppointmentTypes() {
        return appointmentTypeRepository.findAll();
    }

}
