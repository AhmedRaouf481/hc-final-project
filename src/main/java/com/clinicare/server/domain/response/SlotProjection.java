package com.clinicare.server.domain.response;

import java.time.*;

import com.clinicare.server.domain.db.enums.DayOfWeek;

public interface SlotProjection {
    Long getId();
    LocationWithClinicProjection getClinicLocation();
    LocalTime getStartTime();
    DayOfWeek getWeekDay();
}
