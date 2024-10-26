package com.gestionFinanzas.Categories.Investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorias-inversion")
public class InvestmentCategoryController {

    private InvestmentCategoryService investmentCategoryService;

    // Inyección del servicio de categorias de inversión
    @Autowired
    public void setInvestmentCategoryService(InvestmentCategoryService investmentCategoryService) {
        this.investmentCategoryService = investmentCategoryService;
    }

    @GetMapping("/get-all-investment-categories")
    public List<InvestmentCategory> getAllInvestmentCategories() {
        return investmentCategoryService.getAllInvestmentCategories();
    }

}
