package com.gestionFinanzas.Troncal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeOrExpenseRepository extends JpaRepository<IncomeOrExpense, Long> {

    @Query(value = "SELECT i FROM IncomeOrExpense i WHERE i.recurrenceDetails.id = :recurrenceId ORDER BY i.date DESC")
    IncomeOrExpense findByRecurrenceId(@Param("recurrenceId") Long recurrenceId);

    @Query(value = "SELECT i FROM IncomeOrExpense i WHERE i.id = :id")
    IncomeOrExpense findIncomeOrExpenseById(@Param("id") Long id);

    @Query(value = "SELECT COUNT (i) FROM IncomeOrExpense i WHERE i.recurrenceDetails.id = :recurrenceId")
    Long countByRecurrenceId(@Param("recurrenceId") Long recurrenceId);

    @Query("SELECT COUNT(c) FROM IncomeOrExpense c WHERE c.category.id = :categoryId")
    Long countByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT COUNT(c) FROM IncomeOrExpense c WHERE c.subcategory.id = :subccategoryId")
    Long countBySubcategoryId(@Param("subccategoryId") Long subccategoryId);

    @Query(value = "SELECT ioe FROM IncomeOrExpense ioe " +
        " WHERE ioe.user.id = :userId " +
        "     AND (:fromDate IS NULL OR ioe.date >= :fromDate) " +
        "     AND (:toDate IS NULL OR ioe.date <= :toDate) " +
        "     AND (:recurrences IS NULL OR (:recurrences = TRUE AND ioe.recurrenceDetails IS NOT NULL)) " +
        "     AND (:type IS NULL OR ioe.type = :type) " +
        "     AND (:categories IS NULL OR ioe.category.id IN :categories) " +
        "     AND (:subcategories IS NULL OR ioe.subcategory.id IN :subcategories)" +
        "     AND (:currencies IS NULL OR ioe.currency IN :currencies) " +
        "     ORDER BY ioe.date DESC"
    )
    Page<IncomeOrExpense> findByFilters(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("recurrences") Boolean recurrences,
            @Param("type") String type,
            @Param("categories") List<Long> categories,
            @Param("subcategories") List<Long> subcategories,
            @Param("currencies") List<String> currencies,
            @Param("userId") Long userId,
            Pageable pageable
    );
}
