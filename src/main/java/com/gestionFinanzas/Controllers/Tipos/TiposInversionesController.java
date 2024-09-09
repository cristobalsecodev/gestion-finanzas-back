package com.gestionFinanzas.Controllers.Tipos;

import com.gestionFinanzas.Entities.Tipos.TiposInversiones;
import com.gestionFinanzas.Services.Tipos.TiposInversionesService;
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

    @GetMapping("/obtener-todos")
    public List<TiposInversiones> obtenerInversiones() {
        return tiposInversionesService.prueba();
    }

}
