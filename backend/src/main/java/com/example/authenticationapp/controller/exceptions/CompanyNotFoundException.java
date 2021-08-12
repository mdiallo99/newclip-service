package com.example.authenticationapp.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompanyNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * An error company for a address with a wrong id
     * @param id company id
     */
    public CompanyNotFoundException(Long id) {
        super(String.format("Company with id %d not found", id));
    }
}
