package com.gestionFinanzas.Troncales.IncomeOrExpense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeOrExpenseService {

    private IncomeOrExpenseRepository incomeOrExpenseRepository;

    // Inyección del repositorio de ingresos
    @Autowired
    public void setIncomeRepository(IncomeOrExpenseRepository incomeOrExpenseRepository) {
        this.incomeOrExpenseRepository = incomeOrExpenseRepository;
    }

    public List<IncomeOrExpense> getAllIncomes() {
        return incomeOrExpenseRepository.findAll();
    }

}
