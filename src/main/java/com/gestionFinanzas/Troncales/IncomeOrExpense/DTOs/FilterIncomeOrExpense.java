package com.gestionFinanzas.Troncales.IncomeOrExpense.DTOs;

import com.gestionFinanzas.Shared.DTOs.PaginationData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FilterIncomeOrExpense extends PaginationData {

    private List<Long> categories;

    private List<Long> subcategories;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Boolean recurrences;

    private String type;

    private List<String> currencies;

}
