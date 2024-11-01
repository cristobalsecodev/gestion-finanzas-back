package com.gestionFinanzas.Usuarios;

import com.gestionFinanzas.Auth.JwtService;

import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    // Comprobamos el código y activamos la cuenta
    public void activateAccount(String code) {

        final String token = "";
        final String userEmail = jwtService.extractEmail(token);

        String activationCode = userRepository.findActivationCodeByEmail(userEmail);

    }

}
