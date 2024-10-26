package com.gestionFinanzas.Rest.ConversionDivisa.DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRatioDto {
    private String currencyCode;
    private String currencyName;
    private BigDecimal exchangeRate;
}
