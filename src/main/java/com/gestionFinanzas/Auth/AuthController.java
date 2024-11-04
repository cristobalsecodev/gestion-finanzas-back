package com.gestionFinanzas.Auth;

import com.gestionFinanzas.Auth.DTOs.ResetPasswordDto;
import com.gestionFinanzas.Auth.DTOs.TokenResponseDto;
import com.gestionFinanzas.Auth.DTOs.UrlTokenDto;
import com.gestionFinanzas.Auth.DTOs.WantsResetPasswordDto;
import com.gestionFinanzas.OneTimeUrl.OneTimeUrlService;
import com.gestionFinanzas.Usuarios.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final OneTimeUrlService oneTimeUrlService;

    public AuthController(AuthService authService, OneTimeUrlService oneTimeUrlService) {
        this.authService = authService;
        this.oneTimeUrlService = oneTimeUrlService;
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

    @PostMapping("/want-reset-password")
    public ResponseEntity<Map<String, Object>> wantResetPassword(@RequestBody WantsResetPasswordDto wantResetInfo) {

        String result = authService.wantResetPassword(wantResetInfo);

        Map<String, Object> response = new HashMap<>();

        response.put("message", result);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody ResetPasswordDto resetPasswordInfo) {

        String result = authService.resetPassword(resetPasswordInfo);

        Map<String, Object> response = new HashMap<>();

        response.put("message", result);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/check-one-time-url")
    public ResponseEntity<Map<String, Object>> checkOneTimeUrl(@RequestBody UrlTokenDto token) {

        String result = oneTimeUrlService.checkOneTimeUrl(token.getToken());

        Map<String, Object> response = new HashMap<>();

        response.put("message", result);

        return ResponseEntity.ok(response);

    }

}
