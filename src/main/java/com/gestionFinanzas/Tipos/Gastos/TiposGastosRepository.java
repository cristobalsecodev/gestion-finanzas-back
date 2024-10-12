package com.gestionFinanzas.Tipos.Gastos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposGastosRepository extends JpaRepository<TiposGastos, Integer> {
}
