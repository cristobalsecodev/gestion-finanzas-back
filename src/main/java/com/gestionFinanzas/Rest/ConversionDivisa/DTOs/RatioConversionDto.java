package com.gestionFinanzas.Rest.ConversionDivisa.DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RatioConversionDto {
    private String codigoDivisa;
    private String nombreDivisa;
    private BigDecimal valorConversion;
}
