package com.gestionFinanzas.Categories.Subcategories;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Usuarios.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeOrExpenseSubCategoryService {

    private final IncomeOrExpenseSubcategoryRepository incomeOrExpenseSubCategoryRepository;
    private final AuthService authService;

    public IncomeOrExpenseSubCategoryService(
            IncomeOrExpenseSubcategoryRepository incomeOrExpenseSubCategoryRepository,
            AuthService authService
    ) {
        this.incomeOrExpenseSubCategoryRepository = incomeOrExpenseSubCategoryRepository;
        this.authService = authService;
    }

    public List<IncomeOrExpenseSubcategory> getByUserSubCategories() {

        // Recogemos el usuario del security context
        User user = authService.getInfoUser();

        return incomeOrExpenseSubCategoryRepository.findByUser(user.getEmail());

    }

}
