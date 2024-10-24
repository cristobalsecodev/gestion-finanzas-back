package com.gestionFinanzas.Categorias.Ingresos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tipos-ingresos")
public class CategoriasIngresoController {

    private CategoriasIngresoService categoriasIngresoService;

    // Inyección del servicio de tipos ingresos
    @Autowired
    public void setTiposIngresosService(CategoriasIngresoService categoriasIngresoService) {
        this.categoriasIngresoService = categoriasIngresoService;
    }

    @GetMapping("/obtener-todos-tipos-ingresos")
    public List<CategoriasIngreso> obtenerTodosTiposIngresos() {
        return categoriasIngresoService.obtenerTodosTiposIngresos();
    }

}
