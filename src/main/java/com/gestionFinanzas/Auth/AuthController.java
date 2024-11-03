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
    public ResponseEntity<String> wantResetPassword(@RequestBody WantsResetPasswordDto wantResetInfo) {

        return ResponseEntity.ok(authService.wantResetPassword(wantResetInfo));

    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordInfo) {

        return ResponseEntity.ok(authService.resetPassword(resetPasswordInfo));

    }

    @PostMapping("/check-one-time-url")
    public ResponseEntity<String> checkOneTimeUrl(@RequestBody UrlTokenDto token) {

        return ResponseEntity.ok(oneTimeUrlService.checkOneTimeUrl(token.getToken()));

    }

}
