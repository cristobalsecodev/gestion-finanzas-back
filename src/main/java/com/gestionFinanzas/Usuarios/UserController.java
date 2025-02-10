package com.gestionFinanzas.Usuarios;

import com.gestionFinanzas.Auth.DTOs.TokenResponseDto;
import com.gestionFinanzas.Usuarios.DTOs.UserInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/activate-account")
    public ResponseEntity<TokenResponseDto> activateAccount(@RequestBody String activationCode) {

        // Procedemos a la lógica de activación de cuenta
        return ResponseEntity.ok(userService.activateAccount(activationCode));

    }

    @PostMapping("/save-favorite-currency")
    public void saveFavoriteCurrency(@RequestBody String favoriteCurrency) {

        userService.saveFavoriteCurrency(favoriteCurrency);

    }


}
