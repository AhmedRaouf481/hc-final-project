package com.clinicare.server.service;

import java.util.List;

import com.clinicare.server.domain.db.Slot;
import com.clinicare.server.domain.request.SlotRequest;

public interface SlotService {
    Slot addSlot(SlotRequest slot);
    Slot addSlot(Long doctorId,Slot slot);

    List<Slot> getAllSlot();

    Slot getById(Long id);

    Slot updateSlot(Long id, Slot slotDetails);

    void deleteSlot(Long id);
}
