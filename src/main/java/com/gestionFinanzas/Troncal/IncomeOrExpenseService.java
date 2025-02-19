package com.gestionFinanzas.Troncal;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Categories.IncomeOrExpenseCategoryRepository;
import com.gestionFinanzas.Categories.Subcategories.IncomeOrExpenseSubcategoryRepository;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.NotFoundException;
import com.gestionFinanzas.Troncal.DTOs.FilterIncomeOrExpense;
import com.gestionFinanzas.Troncal.RecurrenceDetails.RecurrenceDetailsRepository;
import com.gestionFinanzas.Usuarios.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IncomeOrExpenseService {

    private final IncomeOrExpenseRepository incomeOrExpenseRepository;
    private final RecurrenceDetailsRepository recurrenceDetailsRepository;
    private final AuthService authService;
    private final IncomeOrExpenseCategoryRepository incomeOrExpenseCategoryRepository;
    private final IncomeOrExpenseSubcategoryRepository incomeOrExpenseSubcategoryRepository;

    public IncomeOrExpenseService(
            IncomeOrExpenseRepository incomeOrExpenseRepository,
            RecurrenceDetailsRepository recurrenceDetailsRepository,
            AuthService authService,
            IncomeOrExpenseCategoryRepository incomeOrExpenseCategoryRepository,
            IncomeOrExpenseSubcategoryRepository incomeOrExpenseSubcategoryRepository
    ) {
        this.incomeOrExpenseRepository = incomeOrExpenseRepository;
        this.authService = authService;
        this.recurrenceDetailsRepository = recurrenceDetailsRepository;
        this.incomeOrExpenseCategoryRepository = incomeOrExpenseCategoryRepository;
        this.incomeOrExpenseSubcategoryRepository = incomeOrExpenseSubcategoryRepository;
    }

    public Long saveIncomeOrExpense(IncomeOrExpense incomeOrExpense) {

        // Obtiene el usuario
        User user = this.authService.getInfoUser();

        if(user == null) {

            throw new NotFoundException("User not found");

        }

        // Asigna el usuario al objeto
        incomeOrExpense.setUser(user);

        // Comprueba si la categoría sigue en uso
        checkCategoryUsage(incomeOrExpense);

        // Comprueba si la subcategoría está en uso
        if(incomeOrExpense.getSubcategory() != null) {
            checkSubcategoryUsage(incomeOrExpense);
        }

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

        // Comprueba si la categoría sigue en uso
        checkCategoryUsage(incomeOrExpense);

        // Comprueba si la subcategoría está en uso
        if(incomeOrExpense.getSubcategory() != null) {
            checkSubcategoryUsage(incomeOrExpense);
        }

        return messageDeletion;

    }

    private void checkCategoryUsage(IncomeOrExpense incomeOrExpense) {

        Long countByCategory = incomeOrExpenseRepository.countByCategoryId(incomeOrExpense.getCategory().getId());

        incomeOrExpenseCategoryRepository.updateLinkedCategory(countByCategory != 0, incomeOrExpense.getCategory().getId());

    }

    private void checkSubcategoryUsage(IncomeOrExpense incomeOrExpense) {

        // Comprueba si la subcategoría está en uso
        Long countBySubcategory = incomeOrExpenseRepository.countBySubcategoryId(incomeOrExpense.getSubcategory().getId());

        incomeOrExpenseSubcategoryRepository.updateLinkedSubcategory(countBySubcategory != 0, incomeOrExpense.getSubcategory().getId());

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
