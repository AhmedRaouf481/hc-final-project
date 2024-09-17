package com.clinicare.server.dto;

import com.clinicare.server.domain.db.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Specialization specialization;
}
