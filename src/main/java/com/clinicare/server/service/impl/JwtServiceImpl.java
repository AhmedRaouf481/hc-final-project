package com.clinicare.server.service.impl;

import com.clinicare.server.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String SECRET = "q+b/bFXSxytInnKu5Mp5/KlYyFWSetbqY6DhtLXCfiFVfFhw5bzLaLyAL/7iTALUohu49jtmfR2+ixyUDaFUIP1B8v/SaNi+T4DnsniaK+YY5MXk8FkogLFN60CxLwnG3Gav1nwiLugBUartJ5Q9eL2W0qHxiUwOI6oYw2Rtu+rZpBaSDYcUePHIhtOdCFxz9S00LnBANY+mjSCy+lTInWznfLaztTHa1sHuAgAWgVfVaeUuy+nKgiRh9PF8Lc7SvsokCsQo24wgQ5wjZHv2bWrWDiOV4a/3SC4Jgnwz8g/IfMy98gO3Dt9mVR2ZmOYrURTMj9yIfw8EItN9IJj2+SlMIxTY7JmMfJH8folad+A=";

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return !isTokenExpired(token) && (email.equals(userDetails.getUsername()));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .signWith(getSigningKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .compact();
    }

    // return the claim wanted in this case Username from the payload of the JWT
    @Override
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //  function accepts generic and returns generic, the function takes string token
    // a function that accepts a Claim type and returns a generic of the same type that was accepted.
    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    // getting the payload off the jwt after verifying the signature
    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    // getting signature off the secret to verify it
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(JwtServiceImpl.SECRET));
    }
}

