package com.gestionFinanzas.Troncales.IncomeOrExpense.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class CategorySubcategoriesAssociated {
    private Long category;
    private List<Long> subcategories;
}
