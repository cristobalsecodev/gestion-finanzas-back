package com.gestionFinanzas.Shared.ExceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {

     @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> ApiExceptionHandling(ApiException ex) {
         return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
     }

}
