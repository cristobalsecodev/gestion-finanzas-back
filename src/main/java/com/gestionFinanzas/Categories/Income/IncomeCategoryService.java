package com.gestionFinanzas.Categories.Income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeCategoryService {

    private IncomeCategoryRepository incomeCategoryRepository;

    // Inyección del repositorio de categorias de ingreso
    @Autowired
    public void setIncomeCategoryRepository(IncomeCategoryRepository incomeCategoryRepository) {
        this.incomeCategoryRepository = incomeCategoryRepository;
    }

    public List<IncomeCategory> getAllIncomeCategories() {
        return incomeCategoryRepository.findAll();
    }
}
