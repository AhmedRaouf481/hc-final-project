package com.clinicare.server.controller;

import com.clinicare.server.domain.db.Location;
import com.clinicare.server.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<Location> save(@RequestBody Location location)
    {
        return ResponseEntity.ok(locationService.saveOrUpdate(location));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Location requestLocation)
    {
        Optional<Location> location = locationService.findById(id);
        if (location.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("location doesn't exist");
        requestLocation.setId(location.get().getId());
        return ResponseEntity.ok(locationService.saveOrUpdate(requestLocation));
    }
}
