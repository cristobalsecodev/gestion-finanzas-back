package com.gestionFinanzas.Categories.Subcategories.IncomeOrExpense;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("income-or-expense-subcategories")
public class IncomeOrExpenseSubcategoryController {

    private final IncomeOrExpenseSubCategoryService incomeOrExpenseSubCategoryService;

    public IncomeOrExpenseSubcategoryController(IncomeOrExpenseSubCategoryService incomeOrExpenseSubCategoryService) {
        this.incomeOrExpenseSubCategoryService = incomeOrExpenseSubCategoryService;
    }

    @GetMapping("/get-by-user")
    public List<IncomeOrExpenseSubcategory> getByUserSubCategories() {
        return incomeOrExpenseSubCategoryService.getByUserSubCategories();
    }


}
