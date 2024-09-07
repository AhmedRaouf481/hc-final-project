package com.clinicare.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicare.server.domain.db.Role;
import com.clinicare.server.domain.db.User;
import com.clinicare.server.repository.RoleRepository;
import com.clinicare.server.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // this api mad to test if the user model 
    @GetMapping
    public ResponseEntity<?> test() {
        List<Role> roles = roleRepository.findAllById(List.of(1));
        User user = User.builder()
                .email("ahem@clinicare.com")
                .password("password")
                .username("ahmed")
                .name("Ahmed").phone("01067766132")
                .roles(roles)
                .build();
        return ResponseEntity.ok(userRepository.save(user));
    }

}
