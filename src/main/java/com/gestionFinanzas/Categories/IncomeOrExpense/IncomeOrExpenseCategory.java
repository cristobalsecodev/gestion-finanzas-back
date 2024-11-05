package com.gestionFinanzas.Categories.IncomeOrExpense;

import com.gestionFinanzas.Usuarios.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "income_or_expense_category")
@Data
public class IncomeOrExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, length = 10)
    private String type;  // "income" or "expense"

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

}
