package com.gestionFinanzas.Rest.MarketValuesData.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StockForDayFilterDto {
    private String symbol;
    private LocalDate date;
}
