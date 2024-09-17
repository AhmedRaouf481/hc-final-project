package com.clinicare.server.domain.response;
import java.util.List;

public interface ClinicWithLocationProjection extends BasicClinicProjection {
    List<BasicLocationProjection> getLocations();
}
