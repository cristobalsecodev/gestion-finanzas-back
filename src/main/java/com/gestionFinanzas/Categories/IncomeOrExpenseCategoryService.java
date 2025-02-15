package com.gestionFinanzas.Categories;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Categories.Subcategories.IncomeOrExpenseSubcategory;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.NotFoundException;
import com.gestionFinanzas.Troncal.IncomeOrExpense;
import com.gestionFinanzas.Usuarios.User;
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

    public IncomeOrExpenseCategory saveCategory(IncomeOrExpenseCategory category) {

        // Obtiene el usuario
        User user = this.authService.getInfoUser();

        if(user == null) {

            throw new NotFoundException("User not found");

        }

        // Asigna el usuario al objeto
        category.setUser(user);

        for(IncomeOrExpenseSubcategory subcategory : category.getSubcategories()) {

            subcategory.setUser(user);

        }

        // Asignamos los booleanos por defecto en caso de nuevo registro
        if(category.getId() == null) {

            category.setActive(true);
            category.setLinked(false);

        }

        return incomeOrExpenseCategoryRepository.save(category);

    }

    public List<IncomeOrExpenseCategory> getByUserCategories(Boolean justActives) {

        // Recogemos el usuario del security context
        User user = authService.getInfoUser();

        if(justActives) {

            return incomeOrExpenseCategoryRepository.findByUserActives(user.getEmail());

        } else {

            return incomeOrExpenseCategoryRepository.findByUser(user.getEmail());

        }

    }

    public String deleteCategory(Long id) {

        incomeOrExpenseCategoryRepository.deleteById(id);

        return "The category was successfully deleted";

    }

    public String disableCategory(Long id) {

        incomeOrExpenseCategoryRepository.disableCategory(id);

        return "The category was successfully disabled";

    }

    public String enableCategory(Long id) {

        incomeOrExpenseCategoryRepository.enableCategory(id);

        return "The category was successfully enabled";

    }

}
