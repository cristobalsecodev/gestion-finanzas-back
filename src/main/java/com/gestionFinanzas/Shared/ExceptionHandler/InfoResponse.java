package com.gestionFinanzas.Shared.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InfoResponse {

    private String message;
    private Integer statusCode;

}
