package com.gestionFinanzas.Shared.CurrencyExchangeRatio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("currency-exchange-ratio")
public class CurrencyExchangeRatioController {

    private final CurrencyExchangeRatioService currencyExchangeRatioService;

    public CurrencyExchangeRatioController(CurrencyExchangeRatioService currencyExchangeRatioService) {
        this.currencyExchangeRatioService = currencyExchangeRatioService;
    }

    @GetMapping("/get-currencies")
    public List<CurrencyExchangeRatio> getCurrencies() {
        return currencyExchangeRatioService.getCurrencies();
    }


}
