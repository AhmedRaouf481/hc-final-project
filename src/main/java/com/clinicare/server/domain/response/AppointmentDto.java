package com.clinicare.server.domain.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

// Appointment DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private long id;
    private Long doctorId;
    private String doctorName;

    private Long patientId;
    private String patientName;

    private Long clinicId;
    private String clinicName;

    private Long locationId;
    private String locationAddressLine;
    private String locationCity;

    private Long slotId;
    private LocalTime slotStartTime;

    private LocalDate date;
    
}
