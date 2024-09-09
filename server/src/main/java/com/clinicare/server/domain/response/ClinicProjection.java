package com.clinicare.server.domain.response;

import java.util.List;


public interface ClinicProjection {
    Long getId();
    String getName();
    List<LocationProjection> getLocations();
}
