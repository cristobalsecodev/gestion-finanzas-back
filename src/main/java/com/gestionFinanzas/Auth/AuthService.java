package com.gestionFinanzas.Auth;

import com.gestionFinanzas.Shared.ExceptionHandler.ResourceConflictException;
import com.gestionFinanzas.Usuarios.User;
import com.gestionFinanzas.Usuarios.UserRepository;
import org.springframework.http.ResponseEntity;
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

    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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

    public TokenResponseDto refreshToken(String authorizationHeader) {

        String token = null;

        // Verificamos el token en el header
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            token = authorizationHeader.substring(7);

        }

        // Validamos el token y generamos uno nuevo
        if(token != null) {

            String email = jwtService.extractEmail(token);

            User user = userRepository.findUserByEmail(email);

            if(jwtService.isTokenValid(token, user)) {

                return manageToken(user);

            }

        }

        return null;

    }

    // Gestionamos las claims, la generación del token y su respuesta
    public TokenResponseDto manageToken(User user) {

        // Le añadimos las claims pertinentes
        Map<String, Object> extraClaims = manageClaims(user);

        TokenResponseDto tokenResponse = new TokenResponseDto();

        tokenResponse.setToken(jwtService.generateTokenWithClaims(extraClaims, user));

        // Generamos el token
        return tokenResponse;

    }

    // Maneja las variables extra a meter en el token
    private Map<String, Object> manageClaims(User user) {

        Map<String, Object> extraClaims = new HashMap<>();

        // Determinamos el valor de isAccountActivated
        boolean isActivated = (user.getAccountActivacionCode() == null || user.getAccountActivacionCode().isEmpty());

        extraClaims.put("isAccountActivated", isActivated);

        return extraClaims;

    }

    // Generamos código de activación del usuario
    private String generateActivationCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 6);
    }

}
