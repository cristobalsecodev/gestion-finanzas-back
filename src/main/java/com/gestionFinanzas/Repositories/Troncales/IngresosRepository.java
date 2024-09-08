package com.gestionFinanzas.Repositories.Troncales;

import com.gestionFinanzas.Entities.Troncales.Ingresos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresosRepository extends JpaRepository<Ingresos, Integer> {
}
