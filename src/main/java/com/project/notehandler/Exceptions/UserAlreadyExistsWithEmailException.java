package com.project.notehandler.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserAlreadyExistsWithEmailException extends RuntimeException {
    private String errorMessage;

    public UserAlreadyExistsWithEmailException(String message) {
        super(message);
        this.errorMessage = message;
    }
}
