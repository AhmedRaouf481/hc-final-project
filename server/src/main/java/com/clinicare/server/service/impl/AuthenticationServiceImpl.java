package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.Doctor;
import com.clinicare.server.domain.db.Patient;
import com.clinicare.server.domain.db.Role;
import com.clinicare.server.domain.db.User;
import com.clinicare.server.repository.DoctorRepository;
import com.clinicare.server.repository.PatientRepository;
import com.clinicare.server.repository.RoleRepository;
import com.clinicare.server.repository.UserRepository;
import com.clinicare.server.requests.LoginRequest;
import com.clinicare.server.requests.RegisterRequest;
import com.clinicare.server.response.AuthenticationResponse;
import com.clinicare.server.service.AuthenticationService;
import com.clinicare.server.service.JwtService;
import com.clinicare.server.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Log4j2

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user;
        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findById(3)
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            request.setRoles(new ArrayList<>(Collections.singletonList(defaultRole)));

        }
        // Doctor registration
        if (request.getRoles().stream().anyMatch(role -> role.getId() == 2)) {
            Doctor doctor = Doctor.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phone(request.getPhone())
                    .roles(request.getRoles())
                    .salary(request.getSalary())
                    .summary(request.getSummary())
                    .specialization(request.getSpecialization())
                    .build();
            user = userRepository.save(doctor);
            log.info("Registered doctor: {}", doctor.getEmail());

            // If the user is also a Patient, update roles
            if (request.getRoles().stream().anyMatch(role -> role.getId() == 3)) {
                log.info("created User roles {}", user.getRoles());
                patientService.saveDoctorAsPatient(user.getId());
            }

        }
//        else if()
//        { TODO: Add the logic to create admins with role id = 1 }
        else {
            // patient registration
            Patient patient = Patient.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phone(request.getPhone())
                    .roles(request.getRoles())
                    .build();
            user = userRepository.save(patient);
            log.info("Registered patient: {}", user.getEmail());
        }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .roles(user.getRoles())
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = userRepository.findUserByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

            String jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .roles(user.getRoles())
                    .token(jwtToken).build();
        } catch (AuthenticationException e) {
            log.error("Authentication failed: {}", e.getMessage());
            throw e;
        }
    }

}
