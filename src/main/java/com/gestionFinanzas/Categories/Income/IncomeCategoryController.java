package com.gestionFinanzas.Categories.Income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorias-ingreso")
public class IncomeCategoryController {

    private IncomeCategoryService incomeCategoryService;

    // Inyección del servicio de categorias de ingreso
    @Autowired
    public void setIncomeCategoryService(IncomeCategoryService incomeCategoryService) {
        this.incomeCategoryService = incomeCategoryService;
    }

    @GetMapping("/get-all-income-categories")
    public List<IncomeCategory> getAllIncomeCategories() {
        return incomeCategoryService.getAllIncomeCategories();
    }

}
