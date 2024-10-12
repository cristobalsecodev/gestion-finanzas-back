package com.gestionFinanzas.Troncales.Gastos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastosRepository extends JpaRepository<Gastos, Integer> {
}
