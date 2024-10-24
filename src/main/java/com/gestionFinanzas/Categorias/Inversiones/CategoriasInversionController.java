package com.gestionFinanzas.Categorias.Inversiones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tipos-inversiones")
public class CategoriasInversionController {

    private CategoriasInversionService categoriasInversionService;

    // Inyección del servicio de tipos inversiones
    @Autowired
    public void setTiposInversionesService(CategoriasInversionService categoriasInversionService) {
        this.categoriasInversionService = categoriasInversionService;
    }

    @GetMapping("/obtener-todos-tipos-inversiones")
    public List<CategoriasInversion> obtenerTodosTiposInversiones() {
        return categoriasInversionService.obtenerTodosTiposInversiones();
    }

}
