package com.gestionFinanzas.Categories;

import com.gestionFinanzas.Auth.DTOs.WantsResetPasswordDto;
import com.gestionFinanzas.Troncal.IncomeOrExpense;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("income-or-expense-categories")
public class IncomeOrExpenseCategoryController {

    private final IncomeOrExpenseCategoryService incomeOrExpenseCategoryService;

    public IncomeOrExpenseCategoryController(IncomeOrExpenseCategoryService incomeOrExpenseCategoryService) {
        this.incomeOrExpenseCategoryService = incomeOrExpenseCategoryService;
    }

    @GetMapping("/get-by-user/{justActives}")
    public List<IncomeOrExpenseCategory> getByUserCategories(@PathVariable("justActives") Boolean justActives) {
        return incomeOrExpenseCategoryService.getByUserCategories(justActives);
    }

    @PostMapping("/save")
    public ResponseEntity<IncomeOrExpenseCategory> saveCategory(@RequestBody IncomeOrExpenseCategory category) {

        return ResponseEntity.ok(incomeOrExpenseCategoryService.saveCategory(category));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable("id") Long id) {

        String message = incomeOrExpenseCategoryService.deleteCategory(id);

        Map<String, Object> response = new HashMap<>();

        response.put("message", message);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/disable")
    public ResponseEntity<Map<String, Object>> disableCategory(@RequestBody Long id) {

        String message = incomeOrExpenseCategoryService.disableCategory(id);

        Map<String, Object> response = new HashMap<>();

        response.put("message", message);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/enable")
    public ResponseEntity<Map<String, Object>> enableCategory(@RequestBody Long id) {

        String message = incomeOrExpenseCategoryService.enableCategory(id);

        Map<String, Object> response = new HashMap<>();

        response.put("message", message);

        return ResponseEntity.ok(response);

    }

}
