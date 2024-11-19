package com.gestionFinanzas.Shared.CurrencyExchangeRatio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "currency_exchange_ratio")
@Data
public class CurrencyExchangeRatio {

    @Id
    @Column(name = "currency_code", length = 3, nullable = false, unique = true)
    private String currencyCode;

    @Column(name = "currency_name", nullable = false, length = 100, unique = true)
    private String currencyName;

    @Column(name = "exchange_rate_to_usd", nullable = false, precision = 10, scale = 4)
    private BigDecimal exchangeRateToUsd;

}
