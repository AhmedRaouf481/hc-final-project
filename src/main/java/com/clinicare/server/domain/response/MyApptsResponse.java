package com.clinicare.server.domain.response;

import java.util.List;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MyApptsResponse {
    private List<AppointmentDto> doctor;
    private List<AppointmentDto> patient;
}
