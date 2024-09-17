package com.clinicare.server.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class ValidationException extends RuntimeException {
    private final String msg;

    public ValidationException(String message) {
        super(message);
        this.msg = message;
    }
}
