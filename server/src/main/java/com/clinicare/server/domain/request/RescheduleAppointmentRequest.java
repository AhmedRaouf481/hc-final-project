package com.clinicare.server.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RescheduleAppointmentRequest {

    @NotNull(message = "Slot ID is required")
    private Long slotId;

}