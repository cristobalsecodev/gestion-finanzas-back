package com.gestionFinanzas.Categories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeOrExpenseCategoryRepository extends JpaRepository<IncomeOrExpenseCategory, Long> {

    @Query(value = "SELECT c FROM IncomeOrExpenseCategory c WHERE c.user.email = :email")
    List<IncomeOrExpenseCategory> findByUser(@Param("email") String email);

    @Query(value = "SELECT c FROM IncomeOrExpenseCategory c WHERE c.user.email = :email AND c.active = TRUE")
    List<IncomeOrExpenseCategory> findByUserActives(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE IncomeOrExpenseCategory c SET c.linked = :linked WHERE c.id = :categoryId")
    void updateLinkedCategory(
            @Param("linked") Boolean linked,
            @Param("categoryId") Long categoryId
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE IncomeOrExpenseCategory c SET c.active = FALSE WHERE c.id = :categoryId")
    void disableCategory(
            @Param("categoryId") Long categoryId
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE IncomeOrExpenseCategory c SET c.active = TRUE WHERE c.id = :categoryId")
    void enableCategory(
            @Param("categoryId") Long categoryId
    );


}
