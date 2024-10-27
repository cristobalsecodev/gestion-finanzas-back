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

    // Inyección del servicio de autenticación
    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<InfoResponse> register(@RequestBody User user) {

        authService.register(user);

        return ResponseEntity.ok(new InfoResponse("Registration successfull", 200));

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String password) {

        Map<String, String> response = new HashMap<>();

        response.put("token", authService.login(email, password));

        return ResponseEntity.ok(response);

    }

}
