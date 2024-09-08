package com.gestionFinanzas.Repositories.Tipos;

import com.gestionFinanzas.Entities.Tipos.TiposGastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposGastosRepository extends JpaRepository<TiposGastos, Integer> {
}
