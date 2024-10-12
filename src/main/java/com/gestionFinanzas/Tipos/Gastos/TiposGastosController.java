package com.gestionFinanzas.Tipos.Gastos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tipos-gastos")
public class TiposGastosController {

    private TiposGastosService tiposGastosService;

    // Inyección del servicio de tipos gastos
    @Autowired
    public void setTiposGastosService(TiposGastosService tiposGastosService) {
        this.tiposGastosService = tiposGastosService;
    }

    @GetMapping("obtener-todos-tipos-gastos")
    public List<TiposGastos> obtenerTodosTiposGastos() {
        return tiposGastosService.obtenerTodosTiposGastos();
    }
}
