package com.gestionFinanzas.Categories.Subcategories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeOrExpenseSubcategoryRepository extends JpaRepository<IncomeOrExpenseSubcategory, Long> {

    @Query(value = "SELECT sc FROM IncomeOrExpenseSubcategory sc WHERE sc.user.email = :email")
    List<IncomeOrExpenseSubcategory> findByUser(@Param("email") String email);


    @Transactional
    @Modifying
    @Query(value = "UPDATE IncomeOrExpenseSubcategory sc SET sc.linked = :linked WHERE sc.id = :subcategoryId")
    void updateLinkedSubcategory(
            @Param("linked") Boolean linked,
            @Param("subcategoryId") Long subcategoryId
    );

    List<IncomeOrExpenseSubcategory> findByCategoryId(Long categoryId);

}
