package com.gestionFinanzas.Tipos.Gastos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiposGastosService {

    private TiposGastosRepository tiposGastosRepository;

    // Inyección del repositorio de tipos gastos
    @Autowired
    public void setTiposGastosRepository(TiposGastosRepository tiposGastosRepository) {
        this.tiposGastosRepository = tiposGastosRepository;
    }

    public List<TiposGastos> obtenerTodosTiposGastos() {
        return tiposGastosRepository.findAll();
    }

}
