package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Location;
import com.clinicare.server.exception.ResourceNotFoundException;
import com.clinicare.server.repository.LocationRepository;
import com.clinicare.server.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public Optional<Location> findById(Long id) {
        return Optional.ofNullable(locationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address")));
    }

    @Override
    public Location saveOrUpdate(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }


}
