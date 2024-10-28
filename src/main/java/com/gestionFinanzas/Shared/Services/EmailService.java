package com.gestionFinanzas.Shared.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendActivacionEmail(String emailToSend, String activationCode) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =new MimeMessageHelper(message, true);

        helper.setTo(emailToSend);
        helper.setSubject("Account activation");
        helper.setText("Your activation code is: " + activationCode, true);

        mailSender.send(message);

    }

}
