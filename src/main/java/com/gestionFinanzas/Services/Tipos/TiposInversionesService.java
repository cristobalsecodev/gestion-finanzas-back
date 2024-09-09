package com.gestionFinanzas.Services.Tipos;

import com.gestionFinanzas.Entities.Tipos.TiposInversiones;
import com.gestionFinanzas.Repositories.Tipos.TiposInversionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiposInversionesService {

    private TiposInversionesRepository tiposInversionesRepository;

    // Inyección del repositorio de tipos inversiones
    @Autowired
    public void setTiposInversionesRepository(TiposInversionesRepository tiposInversionesRepository) {
        this.tiposInversionesRepository = tiposInversionesRepository;
    }

    public List<TiposInversiones> prueba() {
        return tiposInversionesRepository.findAll();
    }
}
