package com.gestionFinanzas.Shared.ExceptionHandler;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
