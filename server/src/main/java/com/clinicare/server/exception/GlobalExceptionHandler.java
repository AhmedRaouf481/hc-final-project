package com.clinicare.server.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), badRequest,
        badRequest.value(), request.getRequestURI(), ZonedDateTime.now(ZoneId.of("Z")));

        return ResponseEntity.status(badRequest).body(errorResponse);
    }

    // ******************** Exception handlers for authentication and jwt exceptions *******************
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(Exception ex, HttpServletRequest request) {
        HttpStatus unauthorizedRequest = HttpStatus.UNAUTHORIZED;

        ErrorResponse errorResponse = new ErrorResponse("Unauthorized", unauthorizedRequest,
                unauthorizedRequest.value(), request.getRequestURI(), ZonedDateTime.now(ZoneId.of("Z")));

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleSignatureException(SignatureException ex, HttpServletRequest request) {
        HttpStatus unauthorizedRequest = HttpStatus.UNAUTHORIZED;

        ErrorResponse errorResponse = new ErrorResponse("Invalid token", unauthorizedRequest,
                unauthorizedRequest.value(),
                request.getRequestURI(), ZonedDateTime.now(ZoneId.of("Z")));

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
    // *********************************************
}
