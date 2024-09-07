package com.clinicare.server.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinicare.server.domain.db.Slot;
import com.clinicare.server.exception.ResourceNotFoundException;
import com.clinicare.server.repository.DoctorRepository;
import com.clinicare.server.repository.SlotRepository;
import com.clinicare.server.service.SlotService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository;
    private final DoctorRepository doctorRepository;
    @Override
    public Slot addSlot(Slot slot) {
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
        slot.setEndTime(slotDetails.getEndTime());
        return slotRepository.save(slot);
    }

    @Override
    public void deleteSlot(Long id) {
       Slot slot = slotRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Slot"));
       slotRepository.delete(slot);
    }

    @Override
    public List<Slot> getByDoctorId(Long doctorId) {
        doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor"));
        return slotRepository.findByDoctorId(doctorId);
    }

}
