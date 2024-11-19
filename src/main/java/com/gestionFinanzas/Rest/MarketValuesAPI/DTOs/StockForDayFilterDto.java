package com.gestionFinanzas.Rest.MarketValuesAPI.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StockForDayFilterDto {
    private String symbol;
    private LocalDate date;
}
