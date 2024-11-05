package com.gestionFinanzas.Troncales.IncomeOrExpense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("incomes")
public class IncomeOrExpenseController {

    private IncomeOrExpenseService incomeOrExpenseService;

    // Inyección del servicio de ingresos
    @Autowired
    public void setIncomeService(IncomeOrExpenseService incomeOrExpenseService) {
        this.incomeOrExpenseService = incomeOrExpenseService;
    }

    @GetMapping("/get-all-incomes")
    public List<IncomeOrExpense> getAllIncomes() {
        return incomeOrExpenseService.getAllIncomes();
    }

}
