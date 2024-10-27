package com.gestionFinanzas.Shared.ExceptionHandler;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final Integer statusCode;

    public ApiException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
