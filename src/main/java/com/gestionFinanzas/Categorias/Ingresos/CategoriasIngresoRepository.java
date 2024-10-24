package com.gestionFinanzas.Categorias.Ingresos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriasIngresoRepository extends JpaRepository<CategoriasIngreso, Integer> {
}
