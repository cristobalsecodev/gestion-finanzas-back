package com.gestionFinanzas.Controllers.Tipos;

import com.gestionFinanzas.Entities.Tipos.TiposIngresos;
import com.gestionFinanzas.Services.Tipos.TiposIngresosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tipos-ingresos")
public class TiposIngresosController {

    private TiposIngresosService tiposIngresosService;

    // Inyección del servicio de tipos ingresos
    @Autowired
    public void setTiposIngresosService(TiposIngresosService tiposIngresosService) {
        this.tiposIngresosService = tiposIngresosService;
    }

    @GetMapping("/obtener-todos-tipos-ingresos")
    public List<TiposIngresos> obtenerTodosTiposIngresos() {
        return tiposIngresosService.obtenerTodosTiposIngresos();
    }

}
