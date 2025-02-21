package com.gestionFinanzas.Troncal;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Categories.IncomeOrExpenseCategoryRepository;
import com.gestionFinanzas.Categories.Subcategories.IncomeOrExpenseSubcategoryRepository;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.NotFoundException;
import com.gestionFinanzas.Troncal.DTOs.FilterIncomeOrExpense;
import com.gestionFinanzas.Troncal.RecurrenceDetails.RecurrenceDetailsRepository;
import com.gestionFinanzas.Usuarios.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        Specification<IncomeOrExpense> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Predicate básico usuario
            predicates.add(cb.equal(root.get("user").get("id"), user.getId()));

            // Fechas
            if (filter.getFromDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("transactionDate"), filter.getFromDate()));
            }

            if (filter.getToDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("transactionDate"), filter.getToDate()));
            }

            // Resto de condiciones
            if (filter.getRecurrences() != null) {
                if (filter.getRecurrences()) {
                    predicates.add(cb.isNotNull(root.get("recurrenceDetails")));
                }
            }

            if (filter.getType() != null) {
                predicates.add(cb.equal(root.get("type"), filter.getType()));
            }

            if (filter.getCategories() != null && !filter.getCategories().isEmpty()) {
                predicates.add(root.get("category").get("id").in(filter.getCategories()));
            }

            if (filter.getSubcategories() != null && !filter.getSubcategories().isEmpty()) {
                predicates.add(root.get("subcategory").get("id").in(filter.getSubcategories()));
            }

            if (filter.getCurrencies() != null && !filter.getCurrencies().isEmpty()) {
                predicates.add(root.get("currency").in(filter.getCurrencies()));
            }

            // Ordenamiento
            query.orderBy(cb.desc(root.get("transactionDate")), cb.desc(root.get("id")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return incomeOrExpenseRepository.findAll(spec, pageable);

    }

}
