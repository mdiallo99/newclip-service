package com.example.authenticationapp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderHelper {

    public static PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
