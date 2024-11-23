package com.gestionFinanzas.Troncales.IncomeOrExpense;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Shared.Email.EmailService;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.NotFoundException;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.ResourceConflictException;
import com.gestionFinanzas.Troncales.IncomeOrExpense.DTOs.FilterIncomeOrExpense;
import com.gestionFinanzas.Troncales.IncomeOrExpense.RecurrenceDetails.RecurrenceDetailsRepository;
import com.gestionFinanzas.Usuarios.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeOrExpenseService {

    private final IncomeOrExpenseRepository incomeOrExpenseRepository;

    private final RecurrenceDetailsRepository recurrenceDetailsRepository;

    private final AuthService authService;

    public IncomeOrExpenseService(
            IncomeOrExpenseRepository incomeOrExpenseRepository,
            RecurrenceDetailsRepository recurrenceDetailsRepository,
            AuthService authService
    ) {
        this.incomeOrExpenseRepository = incomeOrExpenseRepository;
        this.authService = authService;
        this.recurrenceDetailsRepository = recurrenceDetailsRepository;
    }

    public Long saveIncomeOrExpense(IncomeOrExpense incomeOrExpense) {

        // Obtiene el usuario
        User user = this.authService.getInfoUser();

        if(user == null) {

            throw new NotFoundException("User not found");

        }

        // Asigna el usuario al objeto
        incomeOrExpense.setUser(user);

        return incomeOrExpenseRepository.save(incomeOrExpense).getId();

    }

    public String deleteIncomeOrExpense(Long id) {

        IncomeOrExpense incomeOrExpense = incomeOrExpenseRepository.findIncomeOrExpenseById(id);

        if(incomeOrExpense == null) {

            throw new ResourceConflictException("The income or expense with id: " + id + " does not exist");

        }

        String recurrenceDeleted = "";

        // Busca cuántos registros hay asignados a esa recurrencia
        if(incomeOrExpense.getRecurrenceDetails() != null) {

            Long howManyIncomeOrExpenses = incomeOrExpenseRepository.countByRecurrenceId(incomeOrExpense.getRecurrenceDetails().getId());

            // Si solo hay uno, elimina la recurrencia
            if(howManyIncomeOrExpenses == 1) {

                recurrenceDeleted = " and the recurrence associated";

                recurrenceDetailsRepository.deleteById(incomeOrExpense.getRecurrenceDetails().getId());

            }

        }

        incomeOrExpenseRepository.deleteById(id);

        return "The " + incomeOrExpense.getType() + recurrenceDeleted + " were successfully deleted";

    }

    public Page<IncomeOrExpense> getFilteredIncomeOrExpenses(
            FilterIncomeOrExpense filter,
            Pageable pageable
    ) {

        // Obtiene el usuario
        User user = this.authService.getInfoUser();

        return incomeOrExpenseRepository.findByFilters(
                filter.getNotes(),
                filter.getCategories(),
                filter.getSubCategories(),
                filter.getStartDate(),
                filter.getEndDate(),
                filter.getRecurrences(),
                filter.getType(),
                filter.getStartAmount(),
                filter.getEndAmount(),
                user.getId(),
                pageable
        );

    }

}
