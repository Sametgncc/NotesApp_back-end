package com.project.notehandler.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsWithUsernameException extends RuntimeException {
    private String errorMessage;

    public UserAlreadyExistsWithUsernameException(String message) {
        super(message);
        this.errorMessage = message;
    }
}
