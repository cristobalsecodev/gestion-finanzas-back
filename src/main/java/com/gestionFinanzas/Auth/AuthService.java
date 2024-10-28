package com.gestionFinanzas.Auth;

import com.gestionFinanzas.Shared.ExceptionHandler.ResourceConflictException;
import com.gestionFinanzas.Usuarios.User;
import com.gestionFinanzas.Usuarios.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Autenticamos el login del usuario
    public User authenticate(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        return userRepository.findUserByEmail(email);

    }

    // Registramos al usuario
    public User signUp(User user) {

        // Comprobamos que el email no existe
        if(userRepository.findUserByEmail(user.getEmail()) != null) {

            throw new ResourceConflictException("The email is already registered", 409);

        }

        // Fecha de creación
        user.setCreationDate(new Date());

        // Codificación de la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Generamos el código de activación y lo asignamos al usuario
        user.setAccountActivacionCode(generateActivationCode());

        // Guardamos el usuario
        return userRepository.save(user);

    }

    // Maneja las variables extra a meter en el token
    public Map<String, Object> manageClaims(User user) {

        Map<String, Object> extraClaims = new HashMap<>();

        // Determinamos el valor de isAccountActivated
        boolean isActivated = (user.getAccountActivacionCode() == null || user.getAccountActivacionCode().isEmpty());

        extraClaims.put("isAccountActivated", isActivated);

        return extraClaims;

    }

    // Generamos el objeto de respuesta del token
    public TokenResponseDto createTokenResponse(String jwtToken, long expirationTime) {

        TokenResponseDto tokenResponseDto = new TokenResponseDto();

        tokenResponseDto.setToken(jwtToken);
        tokenResponseDto.setExpiresIn(expirationTime);

        return tokenResponseDto;

    }

    // Generamos código de activación del usuario
    private String generateActivationCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 6);
    }

}
