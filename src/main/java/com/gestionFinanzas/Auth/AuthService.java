package com.gestionFinanzas.Auth;

import com.gestionFinanzas.Shared.ExceptionHandler.ResourceConflictException;
import com.gestionFinanzas.Usuarios.User;
import com.gestionFinanzas.Usuarios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        return userRepository.findUserByEmail(email);

    }

    public User signUp(User user) {

        if(userRepository.findUserByEmail(user.getEmail()) != null) {

            throw new ResourceConflictException("The email is already registered", 409);

        }

        user.setCreationDate(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }

}
