package com.gestionFinanzas.Repositories.Troncales;

import com.gestionFinanzas.Entities.Troncales.Inversiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InversionesRepository extends JpaRepository<Inversiones, Integer> {
}
