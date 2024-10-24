package com.gestionFinanzas.Categorias.Ingresos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriasIngresoService {

    private CategoriasIngresoRepository categoriasIngresoRepository;

    // Inyección del repositorio de tipos inversiones
    @Autowired
    public void setTiposIngresosRepository(CategoriasIngresoRepository categoriasIngresoRepository) {
        this.categoriasIngresoRepository = categoriasIngresoRepository;
    }

    public List<CategoriasIngreso> obtenerTodosTiposIngresos() {
        return categoriasIngresoRepository.findAll();
    }
}
