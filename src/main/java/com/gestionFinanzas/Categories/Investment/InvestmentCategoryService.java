package com.gestionFinanzas.Categories.Investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentCategoryService {

    private InvestmentCategoryRepository investmentCategoryRepository;

    // Inyección del repositorio de categorias de inversión
    @Autowired
    public void setInvestmentCategoryRepository(InvestmentCategoryRepository investmentCategoryRepository) {
        this.investmentCategoryRepository = investmentCategoryRepository;
    }

    public List<InvestmentCategory> getAllInvestmentCategories() {
        return investmentCategoryRepository.findAll();
    }

}
