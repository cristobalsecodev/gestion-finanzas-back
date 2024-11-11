package com.gestionFinanzas.Troncales.IncomeOrExpense;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("income-or-expense")
public class IncomeOrExpenseController {

    private final IncomeOrExpenseService incomeOrExpenseService;

    public IncomeOrExpenseController(
            IncomeOrExpenseService incomeOrExpenseService
    ) {
        this.incomeOrExpenseService = incomeOrExpenseService;
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveIncomeOrExpense(@RequestBody IncomeOrExpense incomeOrExpense) {

        return ResponseEntity.ok(incomeOrExpenseService.saveIncomeOrExpense(incomeOrExpense));

    }

}
