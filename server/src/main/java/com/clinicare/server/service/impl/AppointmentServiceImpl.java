package com.clinicare.server.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinicare.server.domain.db.Appointment;
import com.clinicare.server.domain.db.AppointmentStatus;
import com.clinicare.server.domain.db.AppointmentType;
import com.clinicare.server.domain.db.Slot;
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

    @Override
    @Transactional
    public Appointment addAppointment(Appointment appointment) {
        Slot slot = validateSlot(appointment.getSlot().getId());

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
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment"));
    }

    @Override
    public Appointment reschaduleAppointment(Long id, Long slotId) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment"));
        Slot slot = validateSlot(slotId);
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

    private Slot validateSlot(Long slotId) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new ResourceNotFoundException("Slot"));
        List<Appointment> existingAppointments = appointmentRepository.findBySlotId(slot.getId());

        if (existingAppointments.isEmpty()) {
            return slot;
        }
        for (Appointment appointment : existingAppointments) {
            if (appointment.getStatus().getId() != 3) {
                throw new ValidationException("Slot with ID " + slot.getId() + " is already booked");
            }
        }

        return slot;

    }
}
