package com.clinicare.server.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex, HttpServletRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), badRequest,
        badRequest.value(), request.getRequestURI(), ZonedDateTime.now(ZoneId.of("Z")));

        return ResponseEntity.status(badRequest).body(errorResponse);
    }

        @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleSQLException(MethodArgumentNotValidException ex,HttpServletRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        System.out.println(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errors, badRequest, badRequest.value(),
        request.getRequestURI(),ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(errorResponse, badRequest);
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
