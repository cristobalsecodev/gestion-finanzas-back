package com.gestionFinanzas.Tipos.Ingresos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposIngresosRepository extends JpaRepository<TiposIngresos, Integer> {
}
