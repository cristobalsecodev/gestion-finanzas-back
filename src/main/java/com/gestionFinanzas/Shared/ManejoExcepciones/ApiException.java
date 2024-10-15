package com.gestionFinanzas.Shared.ManejoExcepciones;

public class ApiException extends RuntimeException {

    private final Integer statusCode;

    public ApiException(String mensaje, Integer statusCode) {
        super(mensaje);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

}
