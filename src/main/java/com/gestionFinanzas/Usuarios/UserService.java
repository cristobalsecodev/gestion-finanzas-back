package com.gestionFinanzas.Usuarios;

import com.gestionFinanzas.Auth.AuthService;

import com.gestionFinanzas.Auth.DTOs.TokenResponseDto;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.ResourceConflictException;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.UnprocessableEntityException;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final AuthService authService;

    public UserService(
            UserRepository userRepository,
            AuthService authService
    ) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    // Comprobamos el código y activamos la cuenta
    public TokenResponseDto activateAccount(String activationCode) {

        // Recogemos el usuario del security context
        User user = authService.getInfoUser();

        // En caso de coincidir, eliminamos el código de activación y pasará a ser una cuenta activada
        if(activationCode.equals(user.getAccountActivacionCode())) {

            userRepository.deleteActivationCodeByEmail(user.getEmail());

        } else {

            throw new UnprocessableEntityException("Code mismatch: the provided code doesn't match the expected one for the email");

        }

        // Como lo único que cambia es el código de activación, en vez de hacer una llamada al repositorio
        // Ponemos el código de activación a null
        user.setAccountActivacionCode(null);

        // Generamos un nuevo token y lo devolvemos
        return authService.manageToken(user);


    }

}
