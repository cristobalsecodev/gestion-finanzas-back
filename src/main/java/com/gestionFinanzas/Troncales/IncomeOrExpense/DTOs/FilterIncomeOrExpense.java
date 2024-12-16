package com.gestionFinanzas.Troncales.IncomeOrExpense.DTOs;

import com.gestionFinanzas.Shared.DTOs.PaginationData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class FilterIncomeOrExpense extends PaginationData {

    private Map<Long, List<Long>> categorySubcategoryMap;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Boolean recurrences;

    private String type;

    private BigDecimal fromAmount;

    private BigDecimal toAmount;

}
