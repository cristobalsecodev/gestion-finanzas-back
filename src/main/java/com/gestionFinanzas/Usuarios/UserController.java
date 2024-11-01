package com.gestionFinanzas.Usuarios;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Auth.TokenResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/activate-account")
    public void signUp(@RequestBody String code, HttpServletRequest request) {

        // Procedemos a la lógica de activación de cuenta
        userService.activateAccount(code);

    }


}
