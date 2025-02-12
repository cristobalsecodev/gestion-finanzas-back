package com.gestionFinanzas.Troncales.IncomeOrExpense;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.NotFoundException;
import com.gestionFinanzas.Troncales.IncomeOrExpense.DTOs.FilterIncomeOrExpense;
import com.gestionFinanzas.Troncales.IncomeOrExpense.RecurrenceDetails.RecurrenceDetailsRepository;
import com.gestionFinanzas.Usuarios.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

            throw new NotFoundException("The income or expense with id: " + id + " does not exist");

        }

        String messageDeletion = "The " + incomeOrExpense.getType() + " was successfully deleted";

        // Busca cuántos registros hay asignados a esa recurrencia
        if(incomeOrExpense.getRecurrenceDetails() != null) {

            Long howManyIncomeOrExpenses = incomeOrExpenseRepository.countByRecurrenceId(incomeOrExpense.getRecurrenceDetails().getId());

            // Si solo hay uno, elimina la recurrencia
            if(howManyIncomeOrExpenses == 1) {

                messageDeletion = "The associated recurrence and the " + incomeOrExpense.getType() + " were successfully deleted";

                recurrenceDetailsRepository.deleteById(incomeOrExpense.getRecurrenceDetails().getId());

            }

        }

        incomeOrExpenseRepository.deleteById(id);

        return messageDeletion;

    }

    public Page<IncomeOrExpense> getFilteredIncomeOrExpenses(
            FilterIncomeOrExpense filter,
            Pageable pageable
    ) {

        // Obtiene el usuario
        User user = this.authService.getInfoUser();

        return incomeOrExpenseRepository.findByFilters(
                filter.getFromDate(),
                filter.getToDate(),
                filter.getRecurrences(),
                filter.getType(),
                filter.getCategories(),
                filter.getSubcategories(),
                filter.getCurrencies(),
                user.getId(),
                pageable
        );

    }

}
