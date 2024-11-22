package com.gestionFinanzas.Troncales.IncomeOrExpense.DTOs;

import com.gestionFinanzas.Shared.DTOs.PaginationData;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class FilterIncomeOrExpense extends PaginationData {

    private String notes;

    private List<String> categories;

    private List<String> subCategories;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<Long> recurrences;

    private String type;

    private BigDecimal startAmount;

    private BigDecimal endAmount;

}
