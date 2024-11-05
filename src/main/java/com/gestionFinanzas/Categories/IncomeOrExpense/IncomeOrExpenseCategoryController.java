package com.gestionFinanzas.Categories.IncomeOrExpense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorias-ingreso")
public class IncomeOrExpenseCategoryController {

    private IncomeOrExpenseCategoryService incomeOrExpenseCategoryService;

    // Inyección del servicio de categorias de ingreso
    @Autowired
    public void setIncomeCategoryService(IncomeOrExpenseCategoryService incomeOrExpenseCategoryService) {
        this.incomeOrExpenseCategoryService = incomeOrExpenseCategoryService;
    }

    @GetMapping("/get-all-income-categories")
    public List<IncomeOrExpenseCategory> getAllIncomeCategories() {
        return incomeOrExpenseCategoryService.getAllIncomeCategories();
    }

}
