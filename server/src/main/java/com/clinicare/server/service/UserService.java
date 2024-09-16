package com.clinicare.server.service;

import com.clinicare.server.domain.db.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findUserByEmail(String token);
}
