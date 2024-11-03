package com.gestionFinanzas.Shared.ExceptionHandler.Exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
