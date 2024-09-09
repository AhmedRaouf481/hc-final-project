package com.clinicare.server.domain.response;

public interface LocationWithClinicProjection extends BasicLocationProjection {
    BasicClinicProjection getClinic();
}
