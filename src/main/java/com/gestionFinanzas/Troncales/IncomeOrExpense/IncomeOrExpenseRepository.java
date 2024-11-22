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
public interface IncomeOrExpenseRepository extends JpaRepository<IncomeOrExpense, Integer> {

    @Query(value = "SELECT i FROM IncomeOrExpense i WHERE i.recurrenceDetails.id = :recurrenceId ORDER BY i.date DESC")
    IncomeOrExpense findByRecurrenceId(@Param("recurrenceId") Long recurrenceId);

    @Query(value = "SELECT COUNT (i) FROM IncomeOrExpense i WHERE i.recurrenceDetails.id = :recurrenceId")
    Long countByRecurrenceId(@Param("recurrenceId") Long recurrenceId);

    @Query("SELECT i FROM IncomeOrExpense i " +
            "LEFT JOIN i.recurrenceDetails r "
//            "WHERE (:notes IS NULL OR LOWER(i.notes) LIKE LOWER(CONCAT('%', :notes, '%'))) " +
//            "AND (:categories IS NULL OR i.category IN :categories) " +
//            "AND (:subCategories IS NULL OR i.subCategory IN :subCategories) " +
//            "AND (:startDate IS NULL OR i.date >= :startDate) " +
//            "AND (:endDate IS NULL OR i.date <= :endDate) " +
//            "AND (:recurrences IS NULL OR r.id IN :recurrences) " +
//            "AND (:type IS NULL OR i.type = :type) " +
//            "AND (:startAmount IS NULL OR i.amount >= :startAmount) " +
//            "AND (:endAmount IS NULL OR i.amount <= :endAmount)"
    )
    Page<IncomeOrExpense> findByFilters(
//            @Param("notes") String notes,
//            @Param("categories") List<String> categories,
//            @Param("subCategories") List<String> subCategories,
//            @Param("startDate") LocalDate startDate,
//            @Param("endDate") LocalDate endDate,
//            @Param("recurrences") List<Long> recurrences,
//            @Param("type") String type,
//            @Param("startAmount") BigDecimal startAmount,
//            @Param("endAmount") BigDecimal endAmount,
            Pageable pageable
    );

}
