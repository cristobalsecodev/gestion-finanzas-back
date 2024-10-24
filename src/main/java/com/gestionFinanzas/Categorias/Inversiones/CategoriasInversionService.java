package com.gestionFinanzas.Categorias.Inversiones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriasInversionService {

    private CategoriasInversionRepository categoriasInversionRepository;

    // Inyección del repositorio de tipos inversiones
    @Autowired
    public void setTiposInversionesRepository(CategoriasInversionRepository categoriasInversionRepository) {
        this.categoriasInversionRepository = categoriasInversionRepository;
    }

    public List<CategoriasInversion> obtenerTodosTiposInversiones() {
        return categoriasInversionRepository.findAll();
    }

}
