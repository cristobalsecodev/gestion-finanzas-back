package com.gestionFinanzas.Categories.IncomeOrExpense;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Usuarios.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeOrExpenseCategoryService {

    private final IncomeOrExpenseCategoryRepository incomeOrExpenseCategoryRepository;
    private final AuthService authService;

    public IncomeOrExpenseCategoryService(
            IncomeOrExpenseCategoryRepository incomeOrExpenseCategoryRepository,
            AuthService authService
    ) {
        this.incomeOrExpenseCategoryRepository = incomeOrExpenseCategoryRepository;
        this.authService = authService;
    }

    public List<IncomeOrExpenseCategory> getByUserCategories() {

        // Recogemos el usuario del security context
        User user = authService.getInfoUser();

        return incomeOrExpenseCategoryRepository.findByUser(user.getEmail());

    }

}
