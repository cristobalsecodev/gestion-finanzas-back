package com.gestionFinanzas.Shared.ExceptionHandler;

import lombok.Getter;

@Getter
public class ResourceConflictException extends RuntimeException {

    private final Integer statusCode;

    public ResourceConflictException(String message, Integer statusCode) {

        super(message);
        this.statusCode = statusCode;
    }

}
