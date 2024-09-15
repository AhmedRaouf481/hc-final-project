package com.clinicare.server.service;

import java.util.List;

import com.clinicare.server.domain.db.Slot;
import com.clinicare.server.domain.db.enums.DayOfWeek;
import com.clinicare.server.domain.response.SlotProjection;

public interface SlotService {
    Slot addSlot(Slot slot);

    List<Slot> getAllSlot(Long doctorId, Long locationId,Long clinicId,  DayOfWeek weekDay);

    Slot getById(Long id);

    List<SlotProjection> getByDoctorId(Long doctorId);

    Slot updateSlot(Long id, Slot slotDetails);

    void deleteSlot(Long id);
}
