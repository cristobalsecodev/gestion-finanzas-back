package com.gestionFinanzas.Tipos.Inversiones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tipos-inversiones")
public class TiposInversionesController {

    private TiposInversionesService tiposInversionesService;

    // Inyección del servicio de tipos inversiones
    @Autowired
    public void setTiposInversionesService(TiposInversionesService tiposInversionesService) {
        this.tiposInversionesService = tiposInversionesService;
    }

    @GetMapping("/obtener-todos-tipos-inversiones")
    public List<TiposInversiones> obtenerTodosTiposInversiones() {
        return tiposInversionesService.obtenerTodosTiposInversiones();
    }

}
