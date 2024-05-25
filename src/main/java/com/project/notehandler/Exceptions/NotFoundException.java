package com.project.notehandler.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private String errorMessage;

    public NotFoundException(String message) {
        super(message);
        this.errorMessage = message;
    }
}
