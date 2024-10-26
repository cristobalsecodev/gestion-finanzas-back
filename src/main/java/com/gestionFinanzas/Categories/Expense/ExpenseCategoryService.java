package com.gestionFinanzas.Categories.Expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseCategoryService {

    private ExpenseCategoryRepository expenseCategoryRepository;

    // Inyección del repositorio de categorias de gasto
    @Autowired
    public void setExpenseCategoriesRepository(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    public List<ExpenseCategory> getAllExpenseCategories() {
        return expenseCategoryRepository.findAll();
    }

}
