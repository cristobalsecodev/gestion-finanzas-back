package com.gestionFinanzas.Rest.MarketValuesData.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class StockForDayFilterDto {
    private String symbol;
    private Date date;
}
