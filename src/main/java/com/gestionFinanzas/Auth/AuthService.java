package com.gestionFinanzas.Auth;

import com.gestionFinanzas.Shared.ExceptionHandler.ResourceConflictException;
import com.gestionFinanzas.Usuarios.User;
import com.gestionFinanzas.Usuarios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private UserRepository userRepository;;

    // Inyección del repositorio de usuario
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(String email, String password) {

        User user = userRepository.findUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // TODO: Meter la generación del token
            return "";
        }

        throw new ResourceConflictException("Invalid credentials", 409);

    }


    public void register(User user) {

        if(userRepository.findUserByEmail(user.getEmail()) != null) {

            throw new ResourceConflictException("This email is already registered", 409);

        }

        user.setCreationDate(new Date());
//        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);

    }

}
