package com.gestionFinanzas.Troncales.IncomeOrExpense;

import com.gestionFinanzas.Troncales.IncomeOrExpense.DTOs.FilterIncomeOrExpense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/filter")
    public ResponseEntity<Page<IncomeOrExpense>> filterIncomeOrExpense(
//            FilterIncomeOrExpense filter,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(incomeOrExpenseService.getFilteredIncomeOrExpenses(
//                filter,
                pageable
        ));

    }


}
