package com.clinicare.server.service;

import com.clinicare.server.domain.db.Location;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface LocationService {
    Optional<Location> findById(Long id);
    Location saveOrUpdate(Location location);
    void delete(Long id);
}
