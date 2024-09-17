package com.clinicare.server.controller;

import com.clinicare.server.service.JwtService;
import com.clinicare.server.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.clinicare.server.domain.db.Role;
import com.clinicare.server.domain.db.User;
import com.clinicare.server.repository.RoleRepository;
import com.clinicare.server.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final JwtService jwtService;

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

    @GetMapping("/profile")
    public ResponseEntity<?> user(@RequestHeader("Authorization") String header) {
        try {
            if (!header.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Authorization header format");
            }

            String token = header.substring(7);

            if (!token.contains(".") || token.split("\\.").length != 3) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT token format");
            }

            String email = jwtService.extractEmail(token);

            return ResponseEntity.ok(userService.findUserByEmail(email));
        } catch (Exception e) {
            log.error("Error processing request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
    }

}
