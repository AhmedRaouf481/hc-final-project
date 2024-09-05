package com.clinicare.server.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.domain.db.Slot;
import com.clinicare.server.domain.request.SlotRequest;
import com.clinicare.server.repository.DoctorRepository;
import com.clinicare.server.repository.SlotRepository;
import com.clinicare.server.service.SlotService;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public Slot addSlot(SlotRequest slot) {
        Doctor doctor = doctorRepository.findById(slot.doctorId()).orElseThrow(()->new ValidationException("doctor not found"));
        Slot newSlot = Slot.builder().startTime(slot.startTime()).endTime(slot.endTime()).doctor(doctor).build();
       return slotRepository.save(newSlot);
    }

    @Override
    public Slot addSlot(Long doctorId, Slot slot) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSlot'");
    }

    @Override
    public List<Slot> getAllSlot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllSlot'");
    }

    @Override
    public Slot getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public Slot updateSlot(Long id, Slot slotDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSlot'");
    }

    @Override
    public void deleteSlot(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSlot'");
    }

}
