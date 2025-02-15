package com.gestionFinanzas.Categories;

import com.gestionFinanzas.Auth.AuthService;
import com.gestionFinanzas.Categories.Subcategories.IncomeOrExpenseSubcategory;
import com.gestionFinanzas.Categories.Subcategories.IncomeOrExpenseSubcategoryRepository;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.NotFoundException;
import com.gestionFinanzas.Troncal.IncomeOrExpense;
import com.gestionFinanzas.Usuarios.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeOrExpenseCategoryService {

    private final IncomeOrExpenseCategoryRepository incomeOrExpenseCategoryRepository;
    private final AuthService authService;
    private final IncomeOrExpenseSubcategoryRepository incomeOrExpenseSubcategoryRepository;

    public IncomeOrExpenseCategoryService(
            IncomeOrExpenseCategoryRepository incomeOrExpenseCategoryRepository,
            AuthService authService,
            IncomeOrExpenseSubcategoryRepository incomeOrExpenseSubcategoryRepository
    ) {
        this.incomeOrExpenseCategoryRepository = incomeOrExpenseCategoryRepository;
        this.authService = authService;
        this.incomeOrExpenseSubcategoryRepository = incomeOrExpenseSubcategoryRepository;
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

        // Recoge los ids de las subcategorías
        List<Long> currentSubcategoryIds = category.getSubcategories().stream()
                .map(IncomeOrExpenseSubcategory::getId)
                .toList();

        List<IncomeOrExpenseSubcategory> existingSubcategories = incomeOrExpenseSubcategoryRepository.findByCategoryId(category.getId());

        // Elimina las subcategorías que no coincidan entre el nuevo objeto y la BBDD
        for (IncomeOrExpenseSubcategory subcategory : existingSubcategories) {

            if (!currentSubcategoryIds.contains(subcategory.getId())) {
                incomeOrExpenseSubcategoryRepository.delete(subcategory);
            }

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
