package com.gestionFinanzas.Categories.Subcategories.Expense;

import com.gestionFinanzas.Categories.Expense.ExpenseCategory;
import com.gestionFinanzas.Usuarios.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "expense_subcategories")
@Data
public class ExpenseSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "expense_category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_expense_category_id"))
    private ExpenseCategory expenseCategory;

}
