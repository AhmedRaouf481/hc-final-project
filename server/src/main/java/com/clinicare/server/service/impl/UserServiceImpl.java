package com.clinicare.server.service.impl;

import com.clinicare.server.domain.db.User;
import com.clinicare.server.exception.ResourceNotFoundException;
import com.clinicare.server.repository.UserRepository;
import com.clinicare.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findUserByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User")
        ));
    }
    }
