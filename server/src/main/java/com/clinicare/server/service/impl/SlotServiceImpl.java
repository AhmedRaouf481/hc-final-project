package com.clinicare.server.service.impl;

import java.time.*;
import java.util.*;

import org.springframework.stereotype.Service;

import com.clinicare.server.domain.db.Slot;
import com.clinicare.server.domain.response.SlotProjection;
import com.clinicare.server.exception.ResourceNotFoundException;
import com.clinicare.server.exception.ValidationException;
import com.clinicare.server.repository.DoctorRepository;
import com.clinicare.server.repository.LocationRepository;
import com.clinicare.server.repository.SlotRepository;
import com.clinicare.server.service.SlotService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {
    private Duration duration = Duration.ofMinutes(30);

    private final SlotRepository slotRepository;
    private final DoctorRepository doctorRepository;
    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public Slot addSlot(Slot slot) {

        if (!doctorRepository.existsById(slot.getDoctor().getId())) {
            throw new ResourceNotFoundException("Doctor");
        }
        if (!locationRepository.existsById(slot.getClinicLocation().getId())) {
            throw new ResourceNotFoundException("Clinic");
        }
        if (!isSlotAvailable(slot.getStartTime(),duration,slot.getDoctor().getId())) {
            throw new ValidationException("The selected time slot conflicts with an existing one.");
        }
        return slotRepository.save(slot);
    }

    @Override
    public List<Slot> getAllSlot() {
        return slotRepository.findAll();
    }

    @Override
    public Slot getById(Long id) {
        return slotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Slot"));
    }

    @Override
    public Slot updateSlot(Long id, Slot slotDetails) {
        Slot slot = slotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Slot"));
        slot.setStartTime(slotDetails.getStartTime());
        slot.setWeekDay(slotDetails.getWeekDay());
        return slotRepository.save(slot);
    }

    @Override
    public void deleteSlot(Long id) {
        Slot slot = slotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Slot"));
        slotRepository.delete(slot);
    }

    @Override
    public List<SlotProjection> getByDoctorId(Long doctorId) {
        doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor"));
        return slotRepository.findByDoctorId(doctorId);
    }

    private boolean isSlotAvailable(LocalTime startTime, Duration duration, Long doctorId) {
        LocalTime endTime = startTime.plus(duration);

        List<SlotProjection> existingSlots = slotRepository.findByDoctorId(doctorId);

        for (SlotProjection slot : existingSlots) {
            LocalTime existingStartTime = slot.getStartTime();
            LocalTime existingEndTime = existingStartTime.plus(duration);

            if (startTime.isBefore(existingEndTime) && endTime.isAfter(existingStartTime)) {
                return false; 
            }
        }

        return true; 
    }


}
