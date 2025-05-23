package com.gestionFinanzas.Shared.ExceptionHandler.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class ApiException extends RuntimeException {

    private final HttpStatusCode statusCode;

    public ApiException(String message, HttpStatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
