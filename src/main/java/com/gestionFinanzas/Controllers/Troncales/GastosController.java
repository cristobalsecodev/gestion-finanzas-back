package com.gestionFinanzas.Controllers.Troncales;

import com.gestionFinanzas.Entities.Troncales.Gastos;
import com.gestionFinanzas.Services.Troncales.GastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("gastos")
public class GastosController {

    private GastosService gastosService;

    // Inyección del servicio de gastos
    @Autowired
    public void setGastosService(GastosService gastosService) {
        this.gastosService = gastosService;
    }

    @GetMapping("/obtener-todos-gastos")
    public List<Gastos> obtenerTodosGastos() {
        return gastosService.obtenerTodosGastos();
    }

}
