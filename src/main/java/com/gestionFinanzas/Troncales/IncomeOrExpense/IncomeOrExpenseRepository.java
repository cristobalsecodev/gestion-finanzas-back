package com.gestionFinanzas.Troncales.IncomeOrExpense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeOrExpenseRepository extends JpaRepository<IncomeOrExpense, Integer> {
}
