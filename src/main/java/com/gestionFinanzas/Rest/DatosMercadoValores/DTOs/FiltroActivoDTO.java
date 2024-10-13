package com.gestionFinanzas.Rest.DatosMercadoValores.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class FiltroActivoDTO {
    private String symbol;
    private String outputSize;
    private String periodType;
    private Date startDate;
    private Date endDate;
}
