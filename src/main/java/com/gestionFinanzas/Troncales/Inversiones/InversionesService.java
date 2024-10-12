package com.gestionFinanzas.Troncales.Inversiones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InversionesService {

    private InversionesRepository inversionesRepository;

    // Inyección del repoository
    @Autowired
    public void setInversionesRepository(InversionesRepository inversionesRepository) {
        this.inversionesRepository = inversionesRepository;
    }

    public List<Inversiones> obtenerTodasInversiones() {
        return inversionesRepository.findAll();
    }

}
