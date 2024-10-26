package com.gestionFinanzas.Troncales.Income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("incomes")
public class IncomeController {

    private IncomeService incomeService;

    // Inyección del servicio de ingresos
    @Autowired
    public void setIncomeService(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/get-all-incomes")
    public List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

}
