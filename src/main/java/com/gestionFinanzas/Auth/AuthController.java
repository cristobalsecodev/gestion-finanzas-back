package com.gestionFinanzas.Auth;

import com.gestionFinanzas.OneTimeUrl.OneTimeUrl;
import com.gestionFinanzas.Usuarios.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<TokenResponseDto> signUp(@RequestBody User user) {

        // Registramos el usuario
        return ResponseEntity.ok(authService.signUp(user));

    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginDto loginInfo) {

        // Autenticamos el usuario
        return ResponseEntity.ok(authService.authenticate(loginInfo.getEmail(), loginInfo.getPassword()));

    }

    @GetMapping("/refresh-token")
    public ResponseEntity<TokenResponseDto> refreshToken(HttpServletRequest request) {

        TokenResponseDto newToken = authService.refreshToken(request);

        if(newToken != null) {

            return ResponseEntity.ok(newToken);

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    }

    @GetMapping("/use-url/{token}")
    public ResponseEntity<String> useOneTimeUrl(@PathVariable String token) {

        return ResponseEntity.ok(authService.useOneTimeUrl(token));

    }

}
