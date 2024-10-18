package com.gestionFinanzas.Rest.ConversionMoneda.DTOs;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ConversionMonedaDto {
    private String result;
    private Date timeLastUpdate;
    private Date timeNextUpdate;
    private String originalCurrency;
    private List<RatioConversionDto> conversionRates;
}
