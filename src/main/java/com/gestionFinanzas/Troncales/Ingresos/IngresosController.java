package com.gestionFinanzas.Troncales.Ingresos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ingresos")
public class IngresosController {

    private IngresosService ingresosService;

    // Inyección del servicio de ingresos
    @Autowired
    public void setIngresosService(IngresosService ingresosService) {
        this.ingresosService = ingresosService;
    }

    @GetMapping("/obtener-todos-ingresos")
    public List<Ingresos> obtenerTodosIngresos() {
        return ingresosService.obtenerTodosIngresos();
    }

}
