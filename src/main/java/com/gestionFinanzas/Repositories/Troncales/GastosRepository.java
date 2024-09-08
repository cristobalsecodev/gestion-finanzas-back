package com.gestionFinanzas.Repositories.Troncales;

import com.gestionFinanzas.Entities.Troncales.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastosRepository extends JpaRepository<Gastos, Integer> {
}
