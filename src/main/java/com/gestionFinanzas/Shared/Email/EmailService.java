package com.gestionFinanzas.Shared.Email;

import com.gestionFinanzas.Shared.OneTimeUrl.OneTimeUrl;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.EmailException;
import com.gestionFinanzas.Usuarios.User;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendActivacionEmail(User user) {

        // Creamos el archivo HTML del correo
        String htmlContent = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>" +
                "<html xmlns='http://www.w3.org/1999/xhtml'>" +
                "<head>" +
                "    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0' />" +
                "    <title>Activation Account</title>" +
                "</head>" +
                "<body style='margin: 0; padding: 0; font-family: Arial, Helvetica, sans-serif; background-color: #f1f5f9;'>" +
                "    <!-- Wrapper for email content -->" +
                "    <table border='0' cellpadding='0' cellspacing='0' width='100%'>" +
                "        <tr>" +
                "            <td align='center' style='padding: 30px 0;'>" +
                "                <!-- Main email container -->" +
                "                <table border='0' cellpadding='0' cellspacing='0' width='600' style='background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);'>" +
                "                    <!-- Header section -->" +
                "                    <tr>" +
                "                        <td align='center' style='padding: 30px 30px 20px 30px; border-bottom: 1px solid #e2e8f0;'>" +
                "                            <!-- Logo circle with icon -->" +
                "                            <table border='0' cellpadding='0' cellspacing='0'>" +
                "                                <tr>" +
                "                                    <td align='center'>" +
                "                                        <img src='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/svgs/solid/user-circle.svg' alt='User icon' width='64' height='64' style='display: block;' />" +
                "                                    </td>" +
                "                                </tr>" +
                "                                <tr>" +
                "                                    <td align='center' style='padding-top: 20px;'>" +
                "                                        <h1 style='margin: 0; font-size: 24px; font-weight: 600; color: #0f172a;'>Welcome to FinanciaSphere</h1>" +
                "                                    </td>" +
                "                                </tr>" +
                "                            </table>" +
                "                        </td>" +
                "                    </tr>" +
                "                    " +
                "                    <!-- Content section -->" +
                "                    <tr>" +
                "                        <td style='padding: 30px;'>" +
                "                            <table border='0' cellpadding='0' cellspacing='0' width='100%'>" +
                "                                <tr>" +
                "                                    <td style='color: #475569; font-size: 16px; line-height: 24px; padding-bottom: 20px;'>" +
                "                                        Thank you for signing up! To complete your account activation, please use the verification code below:" +
                "                                    </td>" +
                "                                </tr>" +
                "                                " +
                "                                <!-- Activation code -->" +
                "                                <tr>" +
                "                                    <td align='center' style='padding: 20px 0;'>" +
                "                                        <table border='0' cellpadding='0' cellspacing='0' width='80%' style='background-color: #e2e8f0; border-radius: 8px;'>" +
                "                                            <tr>" +
                "                                                <td align='center' style='padding: 20px;'>" +
                "                                                    <span style='font-size: 36px; letter-spacing: 6px; font-weight: bold; color: #2563eb; font-family: monospace;'>" + user.getAccountActivacionCode() + "</span>" +
                "                                                </td>" +
                "                                            </tr>" +
                "                                        </table>" +
                "                                    </td>" +
                "                                </tr>" +
                "                                " +
                "                                <!-- Note -->" +
                "                                <tr>" +
                "                                    <td align='center' style='padding-top: 20px; font-size: 14px; color: #64748b;'>" +
                "                                        If you didn't request this activation, please ignore this message. " +
                "                                    </td>" +
                "                                </tr>" +
                "                            </table>" +
                "                        </td>" +
                "                    </tr>" +
                "                    " +
                "                    <!-- Footer section -->" +
                "                    <tr>" +
                "                        <td align='center' style='padding: 20px; background-color: #e2e8f0; border-radius: 0 0 8px 8px; font-size: 12px; color: #64748b;'>" +
                "                            © 2025 FinanciaSphere. All rights reserved." +
                "                        </td>" +
                "                    </tr>" +
                "                </table>" +
                "            </td>" +
                "        </tr>" +
                "    </table>" +
                "</body>" +
                "</html>";

        // Enviamos el email
        sendEmailWithHTML(user.getEmail(),"Account activation", htmlContent);

    }

    public void sendResetPasswordEmail(User user, OneTimeUrl oneTimeUrl) {

        // Creamos el archivo HTML del correo
        String htmlContent = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>" +
                "<html xmlns='http://www.w3.org/1999/xhtml'>" +
                "<head>" +
                "    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0' />" +
                "    <title>Password Reset Request</title>" +
                "</head>" +
                "<body style='margin: 0; padding: 0; font-family: Arial, Helvetica, sans-serif; background-color: #f1f5f9;'>" +
                "    <!-- Wrapper for email content -->" +
                "    <table border='0' cellpadding='0' cellspacing='0' width='100%'>" +
                "        <tr>" +
                "            <td align='center' style='padding: 30px 0;'>" +
                "                <!-- Main email container -->" +
                "                <table border='0' cellpadding='0' cellspacing='0' width='600' style='background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);'>" +
                "                    <!-- Header section -->" +
                "                    <tr>" +
                "                        <td align='center' style='padding: 30px 30px 20px 30px; border-bottom: 1px solid #e2e8f0;'>" +
                "                            <!-- Lock icon -->" +
                "                            <table border='0' cellpadding='0' cellspacing='0'>" +
                "                                <tr>" +
                "                                    <td align='center'>" +
                "                                        <img src='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/svgs/solid/lock.svg' alt='Lock icon' width='64' height='64' style='display: block;' />" +
                "                                    </td>" +
                "                                </tr>" +
                "                                <tr>" +
                "                                    <td align='center' style='padding-top: 20px;'>" +
                "                                        <h1 style='margin: 0; font-size: 24px; font-weight: 600; color: #0f172a;'>Password Reset Request</h1>" +
                "                                    </td>" +
                "                                </tr>" +
                "                            </table>" +
                "                        </td>" +
                "                    </tr>" +
                "                    " +
                "                    <!-- Content section -->" +
                "                    <tr>" +
                "                        <td style='padding: 30px;'>" +
                "                            <table border='0' cellpadding='0' cellspacing='0' width='100%'>" +
                "                                <!-- Greeting -->" +
                "                                <tr>" +
                "                                    <td style='font-weight: 600; color: #0f172a; font-size: 16px; padding-bottom: 15px;'>" +
                "                                        Hello " + user.getName() +"," +
                "                                    </td>" +
                "                                </tr>" +
                "                                " +
                "                                <!-- Message -->" +
                "                                <tr>" +
                "                                    <td style='color: #475569; font-size: 16px; line-height: 24px; padding-bottom: 30px;'>" +
                "                                        We have received a request to reset your password. Click the button below to proceed with creating a new one:" +
                "                                    </td>" +
                "                                </tr>" +
                "                                " +
                "                                <!-- Button -->" +
                "                                <tr>" +
                "                                    <td align='center' style='padding: 20px 0 30px 0;'>" +
                "                                        <!-- Button table -->" +
                "                                        <table border='0' cellpadding='0' cellspacing='0'>" +
                "                                            <tr>" +
                "                                                <td align='center' style='background-color: #2563eb; border-radius: 8px;'>" +
                "                                                    <a href='" + oneTimeUrl.getUrl() + "/" + oneTimeUrl.getToken() + "' target='_blank' style='display: inline-block; padding: 14px 28px; font-family: Arial, sans-serif; font-size: 16px; color: #ffffff; text-decoration: none; font-weight: 600;'>Reset Password</a>" +
                "                                                </td>" +
                "                                            </tr>" +
                "                                        </table>" +
                "                                    </td>" +
                "                                </tr>" +
                "                                " +
                "                                <!-- Note -->" +
                "                                <tr>" +
                "                                    <td align='center' style='font-size: 14px; color: #64748b; line-height: 20px;'>" +
                "                                        Remember that the link is valid for 15 minutes. If you didn't request this password reset, please ignore this message." +
                "                                    </td>" +
                "                                </tr>" +
                "                            </table>" +
                "                        </td>" +
                "                    </tr>" +
                "                    " +
                "                    <!-- Footer section -->" +
                "                    <tr>" +
                "                        <td align='center' style='padding: 20px; background-color: #e2e8f0; border-radius: 0 0 8px 8px; font-size: 12px; color: #64748b;'>" +
                "                            © 2025 FinanciaSphere. All rights reserved." +
                "                        </td>" +
                "                    </tr>" +
                "                </table>" +
                "            </td>" +
                "        </tr>" +
                "    </table>" +
                "</body>" +
                "</html>";

        // Enviamos el email
        sendEmailWithHTML(user.getEmail(),"Reset your password", htmlContent);

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

        } catch (Exception e) {

            throw new EmailException(e.getMessage());

        }



    }

}
