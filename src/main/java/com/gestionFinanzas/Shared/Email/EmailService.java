package com.gestionFinanzas.Shared.Email;

import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.EmailException;
import com.gestionFinanzas.Usuarios.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendActivacionEmail(User user) {

        // Creamos el archivo HTML del correo
        String htmlContent = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<style>" +
                "  body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f7f7f7; }" +
                "  .container { max-width: 600px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); }" +
                "  .header { text-align: center; font-size: 24px; color: #333; }" +
                "  .code { display: block; width: fit-content; margin: 20px auto; padding: 10px 20px; font-size: 32px; letter-spacing: 3px; }" +
                "  .footer { text-align: center; margin-top: 20px; font-size: 14px; color: #555; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "  <div class='container'>" +
                "    <div class='header'>Welcome to FinanciaSphere</div>" +
                "    <p>Thank you for signing up. Please use the following code to activate your account:</p>" +
                "    <div class='code'>" + user.getAccountActivacionCode() + "</div>" +
                "    <p>If you did not request this activation, please ignore this message.</p>" +
                "    <div class='footer'>© 2024 FinanciaSphere. All rights reserved.</div>" +
                "  </div>" +
                "</body>" +
                "</html>";

        // Enviamos el email
        sendEmailWithHTML(user.getEmail(),"Account activation", htmlContent);

    }

    private void sendEmailWithHTML(
            String sendTo,
            String subject,
            String text
    )  {

        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(fromEmail);
            helper.setTo(sendTo);
            helper.setSubject(subject);
            helper.setText(text, true);

            mailSender.send(message);

        } catch(MessagingException e) {

            throw new EmailException("Email sender failed");

        } catch (Exception e) {
            logger.error("An unexpected error occurred: {}", e.getMessage());
            throw new EmailException("Unexpected error while sending activation account email");
        }



    }

}
