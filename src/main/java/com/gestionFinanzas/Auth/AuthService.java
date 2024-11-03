package com.gestionFinanzas.Auth;

import com.gestionFinanzas.Auth.DTOs.ResetPasswordDto;
import com.gestionFinanzas.Auth.DTOs.TokenResponseDto;
import com.gestionFinanzas.Auth.DTOs.WantsResetPasswordDto;
import com.gestionFinanzas.Auth.ENUMs.OneTimeUrlTypeEnum;
import com.gestionFinanzas.OneTimeUrl.OneTimeUrl;
import com.gestionFinanzas.OneTimeUrl.OneTimeUrlService;
import com.gestionFinanzas.Shared.Email.EmailService;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.NotFoundException;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.ResourceConflictException;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.TokenNotFoundException;
import com.gestionFinanzas.Usuarios.User;
import com.gestionFinanzas.Usuarios.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final EmailService emailService;

    private final OneTimeUrlService oneTimeUrlService;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            EmailService emailService,
            OneTimeUrlService oneTimeUrlService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.oneTimeUrlService = oneTimeUrlService;
    }

    // Autenticamos el login del usuario
    public TokenResponseDto authenticate(String email, String password) {

        // Autenticamos
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        // Recuperamos al usuario
        User user = userRepository.findUserByEmail(email);

        // Devolvemos el token
        return manageToken(user);

    }

    // Registramos al usuario
    public TokenResponseDto signUp(User user) {

        // Comprobamos que el email no existe
        if(userRepository.findUserByEmail(user.getEmail()) != null) {

            throw new ResourceConflictException("The email is already registered");

        }

        // Fecha de creación
        user.setCreationDate(new Date());

        // Codificación de la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Generamos el código de activación y lo asignamos al usuario
        user.setAccountActivacionCode(generateActivationCode());

        // Guardamos el usuario
        User registeredUser = userRepository.save(user);

        // Mandamos el email de activación de cuenta
        emailService.sendActivacionEmail(registeredUser);

        // Devolvemos el token
        return manageToken(registeredUser);

    }

    public TokenResponseDto refreshToken(HttpServletRequest request) {

        // Verificamos el token en el header
        String token = extractTokenFromHeader(request);

        // Validamos el token y generamos uno nuevo
        if(token != null) {

            String email = jwtService.extractEmail(token);

            User user = userRepository.findUserByEmail(email);

            if(jwtService.isTokenValid(token, user)) {

                return manageToken(user);

            }

        }

        return null;

    }

    // Gestionamos las claims, la generación del token y su respuesta
    public TokenResponseDto manageToken(User user) {

        // Le añadimos las claims pertinentes
        Map<String, Object> extraClaims = manageClaims(user);

        TokenResponseDto tokenResponse = new TokenResponseDto();

        tokenResponse.setToken(jwtService.generateTokenWithClaims(extraClaims, user));

        // Generamos el token
        return tokenResponse;

    }

    // Función que extrae el token del encabezado
    public String extractTokenFromHeader(HttpServletRequest request) {

        // Extraer el token del encabezado
        String authorizationHeader = request.getHeader("Authorization");
        String jwtToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7); // Extraer el token sin "Bearer "
        }

        if(jwtToken == null) {

            throw new TokenNotFoundException("JWT token is missing");

        }

        return jwtToken;

    }

    // Recuperamos la información del usuario del security context
    public User getInfoUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return (User) principal;
        } else {
            throw new RuntimeException("User not found in security context");
        }

    }

    // Usuario quiere resetear la contraseña
    public String wantResetPassword(WantsResetPasswordDto wantResetInfo) {

        // Creamos una one time url para ello
        OneTimeUrl oneTimeUrlSaved = oneTimeUrlService.createOneTimeUrl(
            wantResetInfo.getUrl(),
            wantResetInfo.getEmail(),
            15,
            OneTimeUrlTypeEnum.RESET_PASS.getName()
        );

        User user = userRepository.findUserByEmail(wantResetInfo.getEmail());

//        emailService.sendResetPasswordEmail(user, oneTimeUrlSaved);

        return "Email sent";

    }

    // Resetear contraseña (el token es el código de la url, no del usuario)
    public String resetPassword(ResetPasswordDto resetPasswordInfo) {

        // Buscamos la oneTimeUrl por su token
        OneTimeUrl oneTimeUrl = oneTimeUrlService.findOneTimeUrlByToken(resetPasswordInfo.getToken());

        if(oneTimeUrl == null) {

            throw new NotFoundException("URL not found");

        }

        // Sacamos el usuario del email
        User user = userRepository.findUserByEmail(oneTimeUrl.getEmail());

        if(user == null) {

            throw new NotFoundException("User not found");

        }

        // Codificación de la contraseña
        user.setPassword(passwordEncoder.encode(resetPasswordInfo.getNewPassword()));

        // Guardamos el usuario
        userRepository.save(user);

        oneTimeUrlService.markOneTimeUrlAsUsed(resetPasswordInfo.getToken());

        return "Your new password has been successfully confirmed. You may now log in";

    }

    // Maneja las variables extra a meter en el token
    private Map<String, Object> manageClaims(User user) {

        Map<String, Object> extraClaims = new HashMap<>();

        // Determinamos el valor de isAccountActivated
        boolean isActivated = (user.getAccountActivacionCode() == null || user.getAccountActivacionCode().isEmpty());

        extraClaims.put("isAccountActivated", isActivated);

        return extraClaims;

    }

    // Generamos código de activación del usuario
    private String generateActivationCode() {

        // Generamos un identificador aleatorio
        String uuid = UUID.randomUUID().toString().replace("-", "");

        // Cogemos los 6 primeros dígitos (excluyendo letras)
        return uuid.replaceAll("[^0-9]", "").substring(0, 6);

    }

}
