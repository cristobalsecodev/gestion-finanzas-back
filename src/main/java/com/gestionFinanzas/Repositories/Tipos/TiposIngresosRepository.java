package com.gestionFinanzas.Repositories.Tipos;

import com.gestionFinanzas.Entities.Tipos.TiposIngresos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposIngresosRepository extends JpaRepository<TiposIngresos, Integer> {
}
