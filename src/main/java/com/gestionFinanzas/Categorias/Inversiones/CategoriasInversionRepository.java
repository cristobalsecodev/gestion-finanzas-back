package com.gestionFinanzas.Categorias.Inversiones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriasInversionRepository extends JpaRepository<CategoriasInversion, Integer> {



}
