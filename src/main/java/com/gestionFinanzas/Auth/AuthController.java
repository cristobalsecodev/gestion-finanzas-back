package com.gestionFinanzas.Auth;

import com.gestionFinanzas.Usuarios.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<TokenResponseDto> signUp(@RequestBody User user) {

        // Procedemos a la lógica de registro de usuario
        User registeredUser = authService.signUp(user);

        return ResponseEntity.ok(authService.manageToken(registeredUser));

    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginDto loginInfo) {

        // Autenticamos el usuario
        User authenticatedUser = authService.authenticate(loginInfo.getEmail(), loginInfo.getPassword());

        return ResponseEntity.ok(authService.manageToken(authenticatedUser));

    }

    @GetMapping("/refresh-token")
    public ResponseEntity<TokenResponseDto> refreshToken(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        TokenResponseDto newToken = authService.refreshToken(authorizationHeader);

        if(newToken != null) {

            return ResponseEntity.ok(newToken);

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    }


}
