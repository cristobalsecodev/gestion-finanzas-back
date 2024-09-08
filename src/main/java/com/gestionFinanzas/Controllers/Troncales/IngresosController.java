package com.gestionFinanzas.Controllers.Troncales;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ingresos")
public class IngresosController {

    @GetMapping("/obtener-todos")
    public String obtenerIngresos() {
        return "Prueba";
    }

}
