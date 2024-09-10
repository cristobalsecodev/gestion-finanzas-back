package com.gestionFinanzas.Controllers.Extra;

import com.gestionFinanzas.Services.Extra.RendimientoAnualInversionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rendimiento-anual-inversiones")
public class RendimientoAnualInversionesController {

    private RendimientoAnualInversionesService rendimientoAnualInversionesService;

    // Inyección del servicio de rendimiento anual inversiones
    @Autowired
    public void setRendimientoAnualInversionesService(RendimientoAnualInversionesService rendimientoAnualInversionesService) {
        this.rendimientoAnualInversionesService = rendimientoAnualInversionesService;
    }

}
