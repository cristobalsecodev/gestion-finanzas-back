package com.gestionFinanzas.Shared.CurrencyExchangeRatio;

import com.gestionFinanzas.APIs.CurrencyExchangeAPIService;
import com.gestionFinanzas.APIs.DTOs.CurrencyExchangeAPIDto;
import com.gestionFinanzas.APIs.DTOs.ExchangeRatioDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyExchangeRatioService {

    private final CurrencyExchangeRatioRepository currencyExchangeRatioRepository;

    private final CurrencyExchangeAPIService currencyExchangeAPIService;

    public CurrencyExchangeRatioService(
            CurrencyExchangeRatioRepository currencyExchangeRatioRepository,
            CurrencyExchangeAPIService currencyExchangeAPIService
    ) {
        this.currencyExchangeRatioRepository = currencyExchangeRatioRepository;
        this.currencyExchangeAPIService = currencyExchangeAPIService;
    }

    public List<CurrencyExchangeRatio> getCurrencies() {

        return currencyExchangeRatioRepository.getCurrencies();

    }

    @Scheduled(cron = "0 0 0 * * ?") // Ejecuta la tarea cada día a medianoche
    public void updateCurrencyExchangeRatios() {

        try {

            // Llamamos a la API para devolver las tasas con el USD como base
            CurrencyExchangeAPIDto conversionDto = currencyExchangeAPIService.currencyConversion("USD");

            // Procesa cada moneda
            List<ExchangeRatioDto> exchangeRates = conversionDto.getExchangeRate();

            exchangeRates.forEach(exchangeRatioDto -> {

                // Busca si la moneda ya existe
                CurrencyExchangeRatio existingCurrency = currencyExchangeRatioRepository.findByCurrencyCode(exchangeRatioDto.getCurrencyCode());

                // Nuevo registro en caso de que la moneda no exista
                if(existingCurrency == null) {

                    CurrencyExchangeRatio newCurrency = new CurrencyExchangeRatio();

                    newCurrency.setCurrencyCode(exchangeRatioDto.getCurrencyCode());
                    newCurrency.setCurrencyName(exchangeRatioDto.getCurrencyName());
                    newCurrency.setExchangeRateToUsd(exchangeRatioDto.getExchangeRate());

                    currencyExchangeRatioRepository.save(newCurrency);

                } else {

                    existingCurrency.setExchangeRateToUsd(exchangeRatioDto.getExchangeRate());

                    currencyExchangeRatioRepository.save(existingCurrency);

                }

            });

        } catch (Exception e) {

            throw new RuntimeException("The update of the currency exchange ratio has failed: " + e.getMessage());

        }

    }

}
