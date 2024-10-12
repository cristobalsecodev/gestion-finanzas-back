package com.gestionFinanzas.Extra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendimientoAnualInversionesService {

    private RendimientoAnualInversionesRepository rendimientoAnualInversionesRepository;

    // Inyección del repositorio de rendimiento anual inversiones
    @Autowired
    public void setRendimientoAnualInversionesRepository(RendimientoAnualInversionesRepository repo) {
        this.rendimientoAnualInversionesRepository = repo;
    }

    public List<RendimientoAnualInversiones> obtenerTodosRendimientosdAnuales() {
        return rendimientoAnualInversionesRepository.findAll();
    }

}
