package com.gestionFinanzas.Rest.MarketValuesData.DTOs;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class StockForDayDto {

    private String afterHours;

    private BigDecimal close;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal open;

    private BigDecimal preMarket;

    private Long volume;

    private Date date;

    private String symbol;

    private String status;
}
