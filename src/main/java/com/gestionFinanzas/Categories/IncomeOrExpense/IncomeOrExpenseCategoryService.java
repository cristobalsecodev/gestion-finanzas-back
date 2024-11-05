package com.gestionFinanzas.Categories.IncomeOrExpense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeOrExpenseCategoryService {

    private IncomeOrExpenseCategoryRepository incomeOrExpenseCategoryRepository;

    // Inyección del repositorio de categorias de ingreso
    @Autowired
    public void setIncomeCategoryRepository(IncomeOrExpenseCategoryRepository incomeOrExpenseCategoryRepository) {
        this.incomeOrExpenseCategoryRepository = incomeOrExpenseCategoryRepository;
    }

    public List<IncomeOrExpenseCategory> getAllIncomeCategories() {
        return incomeOrExpenseCategoryRepository.findAll();
    }
}
