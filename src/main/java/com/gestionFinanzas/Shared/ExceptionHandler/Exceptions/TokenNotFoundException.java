package com.gestionFinanzas.Shared.ExceptionHandler.Exceptions;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
