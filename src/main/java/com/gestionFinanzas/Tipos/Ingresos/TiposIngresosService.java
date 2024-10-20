package com.gestionFinanzas.Tipos.Ingresos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiposIngresosService {

    private TiposIngresosRepository tiposIngresosRepository;

    // Inyección del repositorio de tipos inversiones
    @Autowired
    public void setTiposIngresosRepository(TiposIngresosRepository tiposIngresosRepository) {
        this.tiposIngresosRepository = tiposIngresosRepository;
    }

    public List<TiposIngresos> obtenerTodosTiposIngresos() {
        return tiposIngresosRepository.findAll();
    }
}
