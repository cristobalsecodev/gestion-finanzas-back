package com.gestionFinanzas.Rest.DatosMercadoValores.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class FiltroActivoDiaUnicoDto {
    private String symbol;
    private Date date;
}
