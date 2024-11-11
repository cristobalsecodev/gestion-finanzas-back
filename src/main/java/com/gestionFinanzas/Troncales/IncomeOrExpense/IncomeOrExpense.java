package com.gestionFinanzas.Troncales.IncomeOrExpense;

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

    @Column(nullable = false)
    private String category;

    private String subCategory;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 10)
    private String currency;

    @Column(nullable = false, length = 10)
    private String type;  // "income" or "expense"

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "recurrence_details_id")
    private RecurrenceDetails recurrenceDetails;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

}
