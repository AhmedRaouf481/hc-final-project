package com.clinicare.server.service;

import java.util.List;

import com.clinicare.server.domain.db.Slot;

public interface SlotService {
    Slot addSlot(Slot slot);

    List<Slot> getAllSlot();

    Slot getById(Long id);

    List<Slot> getByDoctorId(Long doctorId);

    Slot updateSlot(Long id, Slot slotDetails);

    void deleteSlot(Long id);
}
