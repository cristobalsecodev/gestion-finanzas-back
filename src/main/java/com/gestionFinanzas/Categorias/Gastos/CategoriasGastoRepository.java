package com.gestionFinanzas.Categorias.Gastos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriasGastoRepository extends JpaRepository<CategoriasGasto, Integer> {
}
