package com.example.authenticationapp.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NullJsonObjectException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NullJsonObjectException() {
        super(String.format("No data found for this object"));
    }
}