package com.gestionFinanzas.Troncales.IncomeOrExpense;

import com.gestionFinanzas.Troncales.IncomeOrExpense.DTOs.CategorySubcategoriesAssociated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface IncomeOrExpenseRepository extends JpaRepository<IncomeOrExpense, Long> {

    @Query(value = "SELECT i FROM IncomeOrExpense i WHERE i.recurrenceDetails.id = :recurrenceId ORDER BY i.date DESC")
    IncomeOrExpense findByRecurrenceId(@Param("recurrenceId") Long recurrenceId);

    @Query(value = "SELECT i FROM IncomeOrExpense i WHERE i.id = :id")
    IncomeOrExpense findIncomeOrExpenseById(@Param("id") Long id);

    @Query(value = "SELECT COUNT (i) FROM IncomeOrExpense i WHERE i.recurrenceDetails.id = :recurrenceId")
    Long countByRecurrenceId(@Param("recurrenceId") Long recurrenceId);

    @Query(value = "SELECT ioe FROM IncomeOrExpense ioe " +
      " WHERE ioe.user.id = :userId " +
      "     AND (:fromDate IS NULL OR ioe.date >= :fromDate) " +
      "     AND (:toDate IS NULL OR ioe.date <= :toDate) " +
      "     AND (:recurrences IS NULL OR (:recurrences = TRUE AND ioe.recurrenceDetails IS NOT NULL)) " +
      "     AND (:type IS NULL OR ioe.type = :type) " +
      "     AND (:fromAmount IS NULL OR ioe.amount >= :fromAmount) " +
      "     AND (:toAmount IS NULL OR ioe.amount <= :toAmount) "
    )
    Page<IncomeOrExpense> findByFilters(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("recurrences") Boolean recurrences,
            @Param("type") String type,
            @Param("fromAmount") BigDecimal fromAmount,
            @Param("toAmount") BigDecimal toAmount,
            @Param("categorySubcategoryList") List<CategorySubcategoriesAssociated> categorySubcategoryList,
            @Param("userId") Long userId,
            Pageable pageable
    );
}
