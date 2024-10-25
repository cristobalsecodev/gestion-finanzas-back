package com.gestionFinanzas.Categorias.Ingresos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriasIngresoService {

    private CategoriasIngresoRepository categoriasIngresoRepository;

    // Inyección del repositorio de categorias de ingreso
    @Autowired
    public void setCategoriasIngresoRepository(CategoriasIngresoRepository categoriasIngresoRepository) {
        this.categoriasIngresoRepository = categoriasIngresoRepository;
    }

    public List<CategoriasIngreso> obtenerTodasCategoriasIngreso() {
        return categoriasIngresoRepository.findAll();
    }
}
