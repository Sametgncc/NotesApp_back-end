package com.project.notehandler.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotMatchedException extends RuntimeException {
    private String errorMessage;

    public NotMatchedException(String message) {
        super(message);
        this.errorMessage = message;
    }
}
