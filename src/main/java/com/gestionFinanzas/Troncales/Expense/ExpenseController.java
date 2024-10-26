package com.gestionFinanzas.Troncales.Expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("expenses")
public class ExpenseController {

    private ExpenseService expenseService;

    // Inyección del servicio de gastos
    @Autowired
    public void setExpenseService(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/get-all-expenses")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

}
