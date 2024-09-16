package com.clinicare.server.dto;

import com.clinicare.server.domain.db.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDTO {
    private Long id;
    private String name;
//    private ArrayList<Location> locations;
    private Location location;
}
