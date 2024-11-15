package com.gestionFinanzas.Categories.Subcategories.IncomeOrExpense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeOrExpenseSubcategoryRepository extends JpaRepository<IncomeOrExpenseSubcategory, Integer> {

    @Query(value = "SELECT iesc FROM IncomeOrExpenseSubcategory iesc WHERE iesc.user.email = :email")
    List<IncomeOrExpenseSubcategory> findByUser(@Param("email") String email);

}
