package com.gestionFinanzas.Categorias.Ingresos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorias-ingreso")
public class CategoriasIngresoController {

    private CategoriasIngresoService categoriasIngresoService;

    // Inyección del servicio de categorias de ingreso
    @Autowired
    public void setCategoriasIngresoService(CategoriasIngresoService categoriasIngresoService) {
        this.categoriasIngresoService = categoriasIngresoService;
    }

    @GetMapping("/obtener-todas-categorias-ingreso")
    public List<CategoriasIngreso> obtenerTodasCategoriasIngreso() {
        return categoriasIngresoService.obtenerTodasCategoriasIngreso();
    }

}
