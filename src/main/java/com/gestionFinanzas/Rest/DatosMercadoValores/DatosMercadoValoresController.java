package com.gestionFinanzas.Rest.DatosMercadoValores;

import com.gestionFinanzas.Rest.DatosMercadoValores.DTOs.ActivoDTO;
import com.gestionFinanzas.Rest.DatosMercadoValores.DTOs.FiltroActivoDTO;
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

    @PostMapping("obtener-info-activo")
    public ActivoDTO obtenerInfoActivo(@RequestBody FiltroActivoDTO filtro) {
        return datosMercadoValoresService.obtenerInfoActivo(filtro);
    }

}
