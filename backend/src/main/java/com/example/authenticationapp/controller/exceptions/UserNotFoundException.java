package com.example.authenticationapp.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * An error message for a user with a wrong email
     * @param email user email
     */
    public UserNotFoundException(String email){
        super(String.format("Ser with email %s not found", email));
    }
}
