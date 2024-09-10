package com.gestionFinanzas.Services.Troncales;

import com.gestionFinanzas.Entities.Troncales.Ingresos;
import com.gestionFinanzas.Repositories.Troncales.IngresosRepository;
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
