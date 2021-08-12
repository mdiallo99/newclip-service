package com.example.authenticationapp.emailManagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class EmailObject {

//    @Autowired
    private JavaMailSender mailSender;

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    abstract void sendEmail();
}
