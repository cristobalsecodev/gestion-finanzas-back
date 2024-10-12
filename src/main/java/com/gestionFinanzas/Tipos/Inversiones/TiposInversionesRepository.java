package com.gestionFinanzas.Tipos.Inversiones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposInversionesRepository extends JpaRepository<TiposInversiones, Integer> {



}
