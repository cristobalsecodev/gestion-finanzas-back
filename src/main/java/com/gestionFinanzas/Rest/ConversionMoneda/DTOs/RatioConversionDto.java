package com.gestionFinanzas.Rest.ConversionMoneda.DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RatioConversionDto {
    private String currencyCode;
    private String currencyName;
    private BigDecimal conversionValue;
}
