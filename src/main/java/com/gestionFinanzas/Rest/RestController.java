package com.gestionFinanzas.Rest;

import com.gestionFinanzas.Rest.CurrencyConversion.CurrencyConversionService;
import com.gestionFinanzas.Rest.CurrencyConversion.DTOs.CurrencyConversionDto;
import com.gestionFinanzas.Rest.MarketValuesData.DTOs.StockForDayDto;
import com.gestionFinanzas.Rest.MarketValuesData.DTOs.TwelveDataResponseDto;
import com.gestionFinanzas.Rest.MarketValuesData.MarketValuesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {

    private MarketValuesDataService marketValuesDataService;

    private CurrencyConversionService currencyConversionService;

    // Inyección del servicio de datos de mercado de valores
    @Autowired
    public void setMarketValuesDataService(MarketValuesDataService marketValuesDataService) {
        this.marketValuesDataService = marketValuesDataService;
    }

    // Inyección del servicio de conversión de moneda
    @Autowired
    public void setCurrencyConversionService(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    // Obtener valores de activo solo por un día
    @PostMapping("get-stock-for-day")
    public StockForDayDto getStockForDay(@RequestBody TwelveDataResponseDto filter) {
        return marketValuesDataService.getStockForDay(filter);
    }

    // Obtener la conversión de todas las divisas a partir de una divisa
    @PostMapping("currency-conversion")
    public CurrencyConversionDto currencyConversion(@RequestBody String currency) {
        return currencyConversionService.currencyConversion(currency);
    }

}
