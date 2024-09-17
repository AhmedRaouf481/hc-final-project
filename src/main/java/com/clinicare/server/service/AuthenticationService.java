package com.clinicare.server.service;

import com.clinicare.server.domain.request.LoginRequest;
import com.clinicare.server.domain.request.RegisterRequest;
import com.clinicare.server.domain.response.AuthenticationResponse;
import org.springframework.stereotype.Service;


@Service
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
}
