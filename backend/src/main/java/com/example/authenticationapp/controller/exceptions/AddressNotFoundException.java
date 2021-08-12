package com.example.authenticationapp.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * An error message for a address with a wrong id
     * @param id address id
     */
    public AddressNotFoundException(Long id) {
        super(String.format("Address with id %d not found", id));
    }
}
