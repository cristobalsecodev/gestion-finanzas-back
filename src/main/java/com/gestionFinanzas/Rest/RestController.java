package com.gestionFinanzas.Rest;

import com.gestionFinanzas.Rest.ConversionDivisa.ConversionDivisaService;
import com.gestionFinanzas.Rest.ConversionDivisa.DTOs.ConversionDivisaDto;
import com.gestionFinanzas.Rest.DatosMercadoValores.DTOs.ActivoDiaUnicoDto;
import com.gestionFinanzas.Rest.DatosMercadoValores.DTOs.FiltroActivoDiaUnicoDto;
import com.gestionFinanzas.Rest.DatosMercadoValores.DatosMercadoValoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {

    private DatosMercadoValoresService datosMercadoValoresService;

    private ConversionDivisaService conversionDivisaService;

    // Inyección del servicio de datos de mercado de valores
    @Autowired
    public void setDatosMercadoValoresService(DatosMercadoValoresService datosMercadoValoresService) {
        this.datosMercadoValoresService = datosMercadoValoresService;
    }

    // Inyección del servicio de conversión de moneda
    @Autowired
    public void setConversionDivisaService(ConversionDivisaService conversionDivisaService) {
        this.conversionDivisaService = conversionDivisaService;
    }

    // Obtener valores de activo solo por un día
    @PostMapping("obtener-activo-unico-dia")
    public ActivoDiaUnicoDto obtenerInfoActivo(@RequestBody FiltroActivoDiaUnicoDto filtro) {
        return datosMercadoValoresService.obtenerInfoActivo(filtro);
    }

    // Obtener la conversión de todas las monedas a partir de una moneda
    @PostMapping("conversion-divisa")
    public ConversionDivisaDto conversionDivisa(@RequestBody String divisa) {
        return conversionDivisaService.conversionDivisa(divisa);
    }

}
