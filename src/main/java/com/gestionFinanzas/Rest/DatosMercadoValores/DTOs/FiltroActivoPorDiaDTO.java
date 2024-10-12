package com.gestionFinanzas.Rest.DatosMercadoValores.DTOs;

import lombok.Data;

@Data
public class FiltroActivoPorDiaDTO {
    private String symbol;
    private String outputSize;
    private String periodType;
}
