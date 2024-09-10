package com.gestionFinanzas.Services.Troncales;

import com.gestionFinanzas.Entities.Troncales.Gastos;
import com.gestionFinanzas.Repositories.Troncales.GastosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastosService {

    private GastosRepository gastosRepository;

    // Inyección del repositorio de gastos
    @Autowired
    public void setGastosRepository(GastosRepository gastosRepository) {
        this.gastosRepository = gastosRepository;
    }

    public List<Gastos> obtenerTodosGastos() {
        return gastosRepository.findAll();
    }
}
