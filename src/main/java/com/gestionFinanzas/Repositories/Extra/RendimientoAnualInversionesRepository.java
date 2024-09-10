package com.gestionFinanzas.Repositories.Extra;

import com.gestionFinanzas.Entities.Extra.RendimientoAnualInversiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendimientoAnualInversionesRepository extends JpaRepository<RendimientoAnualInversiones, Integer> {
}
