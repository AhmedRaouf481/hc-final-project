package com.clinicare.server.domain.request;

import java.time.LocalDateTime;

public record SlotRequest(
    Long doctorId, 
    LocalDateTime startTime,
    LocalDateTime endTime
) {

}
