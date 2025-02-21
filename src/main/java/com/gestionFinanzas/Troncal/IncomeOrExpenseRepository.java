package com.gestionFinanzas.Troncal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeOrExpenseRepository extends JpaRepository<IncomeOrExpense, Long>, JpaSpecificationExecutor<IncomeOrExpense> {

    @Query(value = "SELECT i FROM IncomeOrExpense i WHERE i.recurrenceDetails.id = :recurrenceId ORDER BY i.transactionDate DESC")
    IncomeOrExpense findByRecurrenceId(@Param("recurrenceId") Long recurrenceId);

    @Query(value = "SELECT i FROM IncomeOrExpense i WHERE i.id = :id")
    IncomeOrExpense findIncomeOrExpenseById(@Param("id") Long id);

    @Query(value = "SELECT COUNT (i) FROM IncomeOrExpense i WHERE i.recurrenceDetails.id = :recurrenceId")
    Long countByRecurrenceId(@Param("recurrenceId") Long recurrenceId);

    @Query("SELECT COUNT(i) FROM IncomeOrExpense i WHERE i.category.id = :categoryId")
    Long countByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT COUNT(c) FROM IncomeOrExpense c WHERE c.subcategory.id = :subccategoryId")
    Long countBySubcategoryId(@Param("subccategoryId") Long subccategoryId);

}
