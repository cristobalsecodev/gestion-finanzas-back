package com.gestionFinanzas.Rest;

import com.gestionFinanzas.Rest.MarketValuesAPI.DTOs.StockForDayDto;
import com.gestionFinanzas.Rest.MarketValuesAPI.DTOs.StockForDayFilterDto;
import com.gestionFinanzas.Rest.MarketValuesAPI.MarketValuesAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {

    private MarketValuesAPIService marketValuesAPIService;

    // Inyección del servicio de datos de mercado de valores
    @Autowired
    public void setMarketValuesDataService(MarketValuesAPIService marketValuesAPIService) {
        this.marketValuesAPIService = marketValuesAPIService;
    }

    // Obtener valores de activo solo por un día
    @PostMapping("get-stock-for-day")
    public StockForDayDto getStockForDay(@RequestBody StockForDayFilterDto filter) {
        return marketValuesAPIService.getStockForDay(filter);
    }

}
