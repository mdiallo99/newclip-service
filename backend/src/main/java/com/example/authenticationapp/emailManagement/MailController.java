package com.example.authenticationapp.emailManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

@Controller
public class MailController extends EmailObject {

    @Override
    public void sendEmail(){
        String from = "mdiallopro14@gmail.com";
        String to = "dialloakh@gmail.com";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("This is a plain text email");
        message.setText("Hello guys! This is a plain text email.");

       this.getMailSender().send(message);
    }
}
