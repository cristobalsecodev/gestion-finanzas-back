package com.gestionFinanzas.Rest.ConversionDivisa.DTOs;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CurrencyConversionDto {
    private String result;
    private Date lastUpdatedDate;
    private Date nextUpdateDate;
    private String originalCurrency;
    private List<ExchangeRatioDto> exchangeRate;
}
