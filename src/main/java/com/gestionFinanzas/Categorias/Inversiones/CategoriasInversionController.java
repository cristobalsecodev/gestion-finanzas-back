package com.gestionFinanzas.Categorias.Inversiones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorias-inversion")
public class CategoriasInversionController {

    private CategoriasInversionService categoriasInversionService;

    // Inyección del servicio de categorias de inversión
    @Autowired
    public void setCategoriasInversionService(CategoriasInversionService categoriasInversionService) {
        this.categoriasInversionService = categoriasInversionService;
    }

    @GetMapping("/obtener-todas-categorias-inversion")
    public List<CategoriasInversion> obtenerTodasCategoriasInversion() {
        return categoriasInversionService.obtenerTodasCategoriasInversion();
    }

}
