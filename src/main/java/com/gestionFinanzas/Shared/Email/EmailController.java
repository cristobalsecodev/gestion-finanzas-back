package com.gestionFinanzas.Shared.Email;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Usuarios.User;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    private final AuthService authService;

    public EmailController(EmailService emailService, AuthService authService) {
        this.emailService = emailService;
        this.authService = authService;
    }

    @PostMapping("/send-activation-email")
    public void sendActivacionEmail() {

        // Recogemos el usuario del security context
        User user = authService.getInfoUser();

        // Procedemos al envío del correo con el código de activación
        emailService.sendActivacionEmail(user);

    }

}
