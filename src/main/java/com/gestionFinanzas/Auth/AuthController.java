package com.gestionFinanzas.Auth;

import com.gestionFinanzas.Shared.ExceptionHandler.InfoResponse;
import com.gestionFinanzas.Usuarios.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    private final JwtService jwtService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user) {

        User registeredUser = authService.signUp(user);

        return ResponseEntity.ok(registeredUser);

    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestParam String email, @RequestParam String password) {

        User authenticatedUser = authService.authenticate(email, password);

        String jwtToken = jwtService.generateTokenUser(authenticatedUser);

        TokenResponse tokenResponse = new TokenResponse();

        tokenResponse.setToken(jwtToken);
        tokenResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(tokenResponse);
    }

}
