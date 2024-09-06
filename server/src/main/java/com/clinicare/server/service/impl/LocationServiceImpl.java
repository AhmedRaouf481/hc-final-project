package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Location;
import com.clinicare.server.repository.LocationRepository;
import com.clinicare.server.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Optional<Location> findById(Long id) {
        return Optional.ofNullable(locationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no address was found with this id")));
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
