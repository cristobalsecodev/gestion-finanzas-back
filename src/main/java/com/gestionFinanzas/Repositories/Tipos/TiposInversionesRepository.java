package com.gestionFinanzas.Repositories.Tipos;

import com.gestionFinanzas.Entities.Tipos.TiposInversiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposInversionesRepository extends JpaRepository<TiposInversiones, Integer> {
}
