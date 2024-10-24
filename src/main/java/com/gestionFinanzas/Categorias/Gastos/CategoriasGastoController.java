package com.gestionFinanzas.Categorias.Gastos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tipos-gastos")
public class CategoriasGastoController {

    private CategoriasGastoService categoriasGastoService;

    // Inyección del servicio de tipos gastos
    @Autowired
    public void setTiposGastosService(CategoriasGastoService categoriasGastoService) {
        this.categoriasGastoService = categoriasGastoService;
    }

    @GetMapping("obtener-todos-tipos-gastos")
    public List<CategoriasGasto> obtenerTodosTiposGastos() {
        return categoriasGastoService.obtenerTodosTiposGastos();
    }
}
