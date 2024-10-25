package com.gestionFinanzas.Categorias.Gastos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorias-gasto")
public class CategoriasGastoController {

    private CategoriasGastoService categoriasGastoService;

    // Inyección del servicio de categorías de gasto
    @Autowired
    public void setCategoriasGastoService(CategoriasGastoService categoriasGastoService) {
        this.categoriasGastoService = categoriasGastoService;
    }

    @GetMapping("obtener-todas-categorias-gasto")
    public List<CategoriasGasto> obtenerTodasCategoriasGasto() {
        return categoriasGastoService.obtenerTodasCategoriasGasto();
    }
}
