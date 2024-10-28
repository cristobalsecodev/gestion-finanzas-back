package com.gestionFinanzas.Auth;

import com.gestionFinanzas.Usuarios.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final JwtService jwtService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<TokenResponseDto> signUp(@RequestBody User user) {

        // Procedemos a la lógica de registro de usuario
        User registeredUser = authService.signUp(user);

        return ResponseEntity.ok(manageToken(registeredUser));

    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginDto loginInfo) {

        // Autenticamos el usuario
        User authenticatedUser = authService.authenticate(loginInfo.getEmail(), loginInfo.getPassword());

        return ResponseEntity.ok(manageToken(authenticatedUser));

    }

    // Gestionamos las claims, la generación del token y su respuesta
    private TokenResponseDto manageToken(User user) {

        // Le añadimos las claims pertinentes
        Map<String, Object> extraClaims = authService.manageClaims(user);

        // Generamos el token
        String jwtToken = jwtService.generateTokenWithClaims(extraClaims, user);

        // Creamos el objeto del token y lo devolvemos
        return authService.createTokenResponse(jwtToken, jwtService.getExpirationTime());

    }

}
