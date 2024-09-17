package com.clinicare.server.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

@Service
public interface JwtService {


    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);


    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);


    String extractEmail(String token);


    <T> T extractClaim(String token, Function<Claims, T> claimResolver);


}
