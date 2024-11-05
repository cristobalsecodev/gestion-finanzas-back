package com.gestionFinanzas.Shared.OneTimeUrl;

import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class OneTimeUrlService {

    private final OneTimeUrlRepository oneTimeUrlRepository;

    public OneTimeUrlService(
            OneTimeUrlRepository oneTimeUrlRepository
    ) {

        this.oneTimeUrlRepository = oneTimeUrlRepository;

    }

    // Marca la url como usada
    public void markOneTimeUrlAsUsed(String token) {

        OneTimeUrl oneTimeUrl = oneTimeUrlRepository.findOneTimeUrlByToken(token);

        oneTimeUrl.setUsed(true);

        oneTimeUrlRepository.save(oneTimeUrl);

    }

    // Checkea la url a la que se intenta acceder (El token es el código de la url, no del usuario)
    public String checkOneTimeUrl(String token) {

        OneTimeUrl oneTimeUrl = oneTimeUrlRepository.findOneTimeUrlByToken(token);

        if(oneTimeUrl == null || oneTimeUrl.getUsed() || oneTimeUrl.getExpirationTime() < Instant.now().toEpochMilli()) {

            throw new NotFoundException("The specified URL could not be found or is currently unavailable");

        }

        return "URL validated";

    }

    // Crea una URL única de un uso con expiración a X minutos
    public OneTimeUrl createOneTimeUrl(String url, String email, long expirationTimeInMinutes, String type) {

        // Generamos el token id
        String token = UUID.randomUUID().toString();

        // Convertimos la fecha y hora de ahora en milisegundos
        long currentTimeMillis = Instant.now().toEpochMilli();

        // Convertimos X minutos en milisegundos
        long fifteenMinutesInMillis = expirationTimeInMinutes * 60 * 1000;

        // Sumamos los milisegundos
        long newTimeMillis = currentTimeMillis + fifteenMinutesInMillis;

        OneTimeUrl oneTimeUrl = new OneTimeUrl();

        oneTimeUrl.setToken(token);
        oneTimeUrl.setUrl(url);
        oneTimeUrl.setEmail(email);
        oneTimeUrl.setExpirationTime(newTimeMillis);
        oneTimeUrl.setUsed(false);
        oneTimeUrl.setType(type);

        // Guardamos la url
        return oneTimeUrlRepository.save(oneTimeUrl);

    }

    public OneTimeUrl findOneTimeUrlByToken(String token) {

       return oneTimeUrlRepository.findOneTimeUrlByToken(token);

    }


}
