package com.gestionFinanzas.Troncales.Ingresos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresosRepository extends JpaRepository<Ingresos, Integer> {
}
