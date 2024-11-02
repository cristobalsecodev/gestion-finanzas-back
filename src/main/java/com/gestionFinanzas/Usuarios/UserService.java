package com.gestionFinanzas.Usuarios;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Auth.JwtService;

import com.gestionFinanzas.Auth.TokenResponseDto;
import com.gestionFinanzas.Shared.ExceptionHandler.ResourceConflictException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthService authService;

    public UserService(
            UserRepository userRepository,
            JwtService jwtService,
            AuthService authService
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    // Comprobamos el código y activamos la cuenta
    public TokenResponseDto activateAccount(String activationCode, HttpServletRequest request) {

        // Extraemos el token del header
        String token = authService.extractTokenFromHeader(request);

        // Decodificamos y extraemos el email del token
        final String userEmail = jwtService.extractEmail(token);

        // Recuperamos el código de activación
        String activationCodeGenerated = userRepository.findActivationCodeByEmail(userEmail);

        // En caso de coincidir, eliminamos el código de activación y pasará a ser una cuenta activada
        if(activationCode.equals(activationCodeGenerated)) {

            userRepository.deleteActivationCodeByEmail(userEmail);

        } else {

            throw new ResourceConflictException("Code mismatch: the provided code doesn't match the expected one for the email");

        }

        // Generamos un nuevo token y lo devolvemos
        return authService.manageToken(userRepository.findUserByEmail(userEmail));


    }

}
