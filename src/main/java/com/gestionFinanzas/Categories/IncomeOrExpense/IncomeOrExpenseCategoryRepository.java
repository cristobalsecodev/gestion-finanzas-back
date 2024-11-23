package com.gestionFinanzas.Categories.IncomeOrExpense;

import com.gestionFinanzas.Troncales.IncomeOrExpense.IncomeOrExpense;
import com.gestionFinanzas.Usuarios.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeOrExpenseCategoryRepository extends JpaRepository<IncomeOrExpenseCategory, Long> {

    @Query(value = "SELECT iec FROM IncomeOrExpenseCategory iec WHERE iec.user.email = :email")
    List<IncomeOrExpenseCategory> findByUser(@Param("email") String email);


}
