package com.gestionFinanzas.Troncales.Inversiones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("inversiones")
public class InversionesController {

    private InversionesService inversionesService;

    // Inyección del servicio de inversiones
    @Autowired
    public void setInversionesService(InversionesService inversionesService) {
        this.inversionesService = inversionesService;
    }

    @GetMapping("/obtener-todas-inversiones")
    public List<Inversiones> obtenerTodasInversiones() {
        return inversionesService.obtenerTodasInversiones();
    }

}
