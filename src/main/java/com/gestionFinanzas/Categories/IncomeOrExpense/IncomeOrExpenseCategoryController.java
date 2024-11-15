package com.gestionFinanzas.Categories.IncomeOrExpense;

import com.gestionFinanzas.Usuarios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("income-or-expense-categories")
public class IncomeOrExpenseCategoryController {

    private final IncomeOrExpenseCategoryService incomeOrExpenseCategoryService;

    public IncomeOrExpenseCategoryController(IncomeOrExpenseCategoryService incomeOrExpenseCategoryService) {
        this.incomeOrExpenseCategoryService = incomeOrExpenseCategoryService;
    }

    @GetMapping("/get-by-user")
    public List<IncomeOrExpenseCategory> getByUserCategories() {
        return incomeOrExpenseCategoryService.getByUserCategories();
    }

}
