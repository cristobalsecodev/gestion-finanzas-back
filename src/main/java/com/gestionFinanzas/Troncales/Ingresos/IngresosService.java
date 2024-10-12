package com.gestionFinanzas.Troncales.Ingresos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngresosService {

    private IngresosRepository ingresosRepository;

    // Inyección del repositorio de ingresos
    @Autowired
    public void setIngresosRepository(IngresosRepository ingresosRepository) {
        this.ingresosRepository = ingresosRepository;
    }

    public List<Ingresos> obtenerTodosIngresos() {
        return ingresosRepository.findAll();
    }

}
