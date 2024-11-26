package com.gestionFinanzas.Troncales.IncomeOrExpense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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

    @Query("SELECT i FROM IncomeOrExpense i " +
            "WHERE (:notes IS NULL OR (i.notes = :notes)) " +
            "AND (:categories IS NULL OR i.category.id IN :categories) " +
            "AND (:subcategories IS NULL OR i.subcategory.id IN :subcategories) " +
            "AND (:startDate IS NULL OR i.date >= :startDate) " +
            "AND (:endDate IS NULL OR i.date <= :endDate) " +
            "AND (:recurrences IS NULL OR i.recurrenceDetails.id IN :recurrences) " +
            "AND (:type IS NULL OR i.type = :type) " +
            "AND (:startAmount IS NULL OR i.amount >= :startAmount) " +
            "AND (:endAmount IS NULL OR i.amount <= :endAmount) " +
            "AND (:userId IS NULL OR i.user.id = :userId) "
    )
    Page<IncomeOrExpense> findByFilters(
            @Param("notes") String notes,
            @Param("categories") List<Long> categories,
            @Param("subcategories") List<Long> subcategories,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("recurrences") List<Long> recurrences,
            @Param("type") String type,
            @Param("startAmount") BigDecimal startAmount,
            @Param("endAmount") BigDecimal endAmount,
            @Param("userId") Long userId,
            Pageable pageable
    );
}
