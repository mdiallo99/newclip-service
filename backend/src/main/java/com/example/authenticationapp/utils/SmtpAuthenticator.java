package com.example.authenticationapp.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator {
    public SmtpAuthenticator() {

        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "mdiallopro14@gmail.com";
        String password = "DialloPro/14";

        return new PasswordAuthentication(username, password);
    }
}