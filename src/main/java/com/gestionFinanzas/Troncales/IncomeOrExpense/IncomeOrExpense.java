package com.gestionFinanzas.Troncales.IncomeOrExpense;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestionFinanzas.Categories.IncomeOrExpense.IncomeOrExpenseCategory;
import com.gestionFinanzas.Categories.Subcategories.IncomeOrExpense.IncomeOrExpenseSubcategory;
import com.gestionFinanzas.Troncales.IncomeOrExpense.RecurrenceDetails.RecurrenceDetails;
import com.gestionFinanzas.Usuarios.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "income_or_expense")
@Data
public class IncomeOrExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 10)
    private String currency;

    @Column(name = "exchange_rate_to_usd", nullable = false, precision = 10, scale = 4)
    private BigDecimal exchangeRateToUsd;

    @Column(nullable = false, length = 10)
    private String type;  // "income" or "expense"

    @Column(length = 150)
    private String notes;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "recurrence_details_id")
    private RecurrenceDetails recurrenceDetails;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private IncomeOrExpenseCategory category;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private IncomeOrExpenseSubcategory subcategory;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

}
