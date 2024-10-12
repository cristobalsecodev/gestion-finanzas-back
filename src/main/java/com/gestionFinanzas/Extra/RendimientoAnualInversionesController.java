package com.gestionFinanzas.Extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rendimiento-anual-inversiones")
public class RendimientoAnualInversionesController {

    private RendimientoAnualInversionesService rendimientoAnualInversionesService;

    // Inyección del servicio de rendimiento anual inversiones
    @Autowired
    public void setRendimientoAnualInversionesService(RendimientoAnualInversionesService rendimientoAnualInversionesService) {
        this.rendimientoAnualInversionesService = rendimientoAnualInversionesService;
    }

    @GetMapping("obtener-todos-rendimientos-anuales")
    public List<RendimientoAnualInversiones> obtenerTodosRendimientosdAnuales() {
        return rendimientoAnualInversionesService.obtenerTodosRendimientosdAnuales();
    }

}
