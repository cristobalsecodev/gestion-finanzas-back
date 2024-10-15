package com.gestionFinanzas.Rest.DatosMercadoValores;

import com.gestionFinanzas.Rest.DatosMercadoValores.DTOs.ActivoDiaUnicoDto;
import com.gestionFinanzas.Rest.DatosMercadoValores.DTOs.FiltroActivoDiaUnicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("datos-mercado-valores")
public class DatosMercadoValoresController {

    private DatosMercadoValoresService datosMercadoValoresService;

    // Inyección del servicio de datos de mercado de valores
    @Autowired
    public void setDatosMercadoValoresService(DatosMercadoValoresService datosMercadoValoresService) {
        this.datosMercadoValoresService = datosMercadoValoresService;
    }

    @PostMapping("obtener-activo-unico-dia")
    public ActivoDiaUnicoDto obtenerInfoActivo(@RequestBody FiltroActivoDiaUnicoDto filtro) {
        return datosMercadoValoresService.obtenerInfoActivo(filtro);
    }

}
