package com.gestionFinanzas.Shared.ExceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<InfoResponse> ApiExceptionHandling(ApiException ex) {
         return buildResponse(ex.getMessage(), ex.getStatusCode());
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<InfoResponse> ResourceConflictExceptionHandling(ResourceConflictException ex) {
        return buildResponse(ex.getMessage(), ex.getStatusCode());
    }

    private ResponseEntity<InfoResponse> buildResponse(String message, Integer statusCode) {
        return ResponseEntity.status(statusCode).body(new InfoResponse(message, statusCode));
    }

}
