package com.gestionFinanzas.Shared.ManejoExcepciones;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManejoExcepcionesGlobal {

     @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> manejoApiException(ApiException ex) {
         return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
     }

}
