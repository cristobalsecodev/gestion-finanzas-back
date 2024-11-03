package com.gestionFinanzas.Auth;

import com.gestionFinanzas.Usuarios.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    // Obtiene el email del token JWT, se usa extraClaims para devolver el subject (identificador del usuario)
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extrae un campo específico del token mediante el claimsResolver
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Genera un token JWT simple para el usuario
    public String generateTokenUser(UserDetails userDetails) {
        return generateTokenWithClaims(new HashMap<>(), userDetails);
    }

    // Genera un token con extraClaims personalizados
    public String generateTokenWithClaims(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    // Crea el token JWT
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .claims(extraClaims) // Añade datos personalizados al token (roles, permisos...)
                .subject(userDetails.getUsername()) // Identificador único del usuario
                .issuedAt(new Date(System.currentTimeMillis())) // Indica la hora y fecha exacta en la que se generó el token
                .expiration(new Date(System.currentTimeMillis() + expiration)) // Indica la fecha y hora de expiración mediante variable
                .signWith(getSignInKey()) // Firma del token usando clave secreta
                .compact(); // Compacta y serializa el token en una cadena con formato JWT
    }

    // Comprueba si el token es válido para el usuario
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Comprueba si el token del usuario ha expirado
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extrae la fecha de expiración del token mediante un extraClaim
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Devuelve todos los claims del token, usando la clave secreta para la verificación
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Genera una clave secreta para firmar y verificar el token
    public SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
