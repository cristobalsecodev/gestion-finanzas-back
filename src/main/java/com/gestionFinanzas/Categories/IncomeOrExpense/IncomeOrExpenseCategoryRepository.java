package com.gestionFinanzas.Categories.IncomeOrExpense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeOrExpenseCategoryRepository extends JpaRepository<IncomeOrExpenseCategory, Integer> {
}
