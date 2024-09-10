package com.gestionFinanzas.Services.Extra;

import com.gestionFinanzas.Repositories.Extra.RendimientoAnualInversionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RendimientoAnualInversionesService {

    private RendimientoAnualInversionesRepository rendimientoAnualInversionesRepository;

    // Inyección del repositorio de rendimiento anual inversiones
    @Autowired
    public void setRendimientoAnualInversionesRepository(RendimientoAnualInversionesRepository repo) {
        this.rendimientoAnualInversionesRepository = repo;
    }

}
