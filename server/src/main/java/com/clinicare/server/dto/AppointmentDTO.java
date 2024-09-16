package com.clinicare.server.dto;

import com.clinicare.server.domain.db.AppointmentType;
import com.clinicare.server.domain.db.Clinic;
import com.clinicare.server.domain.db.Slot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

    private Long id;
    private LocalDate date;
    private AppointmentType type;
    private SlotDTO slot;
    private ClinicDTO clinic;
}
