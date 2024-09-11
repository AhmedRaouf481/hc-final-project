package com.clinicare.server.service;

import com.clinicare.server.requests.LoginRequest;
import com.clinicare.server.requests.RegisterRequest;
import com.clinicare.server.response.AuthenticationResponse;
import org.springframework.stereotype.Service;


@Service
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
}
