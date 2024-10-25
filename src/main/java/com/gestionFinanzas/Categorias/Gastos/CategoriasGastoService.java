package com.gestionFinanzas.Categorias.Gastos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriasGastoService {

    private CategoriasGastoRepository categoriasGastoRepository;

    // Inyección del repositorio de categorias de gasto
    @Autowired
    public void setCategoriasGastoRepository(CategoriasGastoRepository categoriasGastoRepository) {
        this.categoriasGastoRepository = categoriasGastoRepository;
    }

    public List<CategoriasGasto> obtenerTodasCategoriasGasto() {
        return categoriasGastoRepository.findAll();
    }

}
