package com.clinicare.server.domain.response;

import org.springframework.stereotype.Component;

import com.clinicare.server.domain.db.Appointment;

@Component
public class DtoMapper {

    // Method to map Appointment entity to flat AppointmentDto
    public AppointmentDto mapToAppointmentDto(Appointment appointment) {
        AppointmentDto dto = new AppointmentDto();
        
        dto.setId(appointment.getId());
        
        // Mapping doctor information
        dto.setDoctorId(appointment.getSlot().getDoctor().getId());
        dto.setDoctorName(appointment.getSlot().getDoctor().getName());

        // Mapping patient information
        dto.setPatientId(appointment.getPatient().getId());
        dto.setPatientName(appointment.getPatient().getName());

        // Mapping clinic information
        dto.setClinicId(appointment.getSlot().getClinicLocation().getClinic().getId());
        dto.setClinicName(appointment.getSlot().getClinicLocation().getClinic().getName());

        // Mapping clinic location information
        dto.setLocationId(appointment.getSlot().getClinicLocation().getId());
        dto.setLocationAddressLine(appointment.getSlot().getClinicLocation().getAddressLine());
        dto.setLocationCity(appointment.getSlot().getClinicLocation().getCity());

        // Mapping slot information
        dto.setSlotId(appointment.getSlot().getId());
        dto.setSlotStartTime(appointment.getSlot().getStartTime());

        // Mapping appointment date
        dto.setDate(appointment.getDate());

        return dto;
    }
}
