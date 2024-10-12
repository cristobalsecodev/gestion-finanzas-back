package com.gestionFinanzas.Rest.DatosMercadoValores.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ActivoPorDiaDTO {
    @JsonProperty("1. Information")
    private String information;

    @JsonProperty("2. Symbol")
    private String symbol;

    @JsonProperty("3. Last Refreshed")
    private Date lastRefreshed;

    @JsonProperty("4. Output Size")
    private String outputSize;

    @JsonProperty("5. Time Zone")
    private String timeZone;

    private List<DailyTradingValuesDto> dailyValues;
}