package com.gestionFinanzas.Services.Troncales;

import com.gestionFinanzas.Entities.Troncales.Inversiones;
import com.gestionFinanzas.Repositories.Troncales.InversionesRepository;
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
