package com.gestionFinanzas.Categories.Subcategories;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestionFinanzas.Categories.IncomeOrExpenseCategory;
import com.gestionFinanzas.Usuarios.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "income_or_expense_subcategory",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_name_type_user_category",
                        columnNames = {"name", "type", "user_id", "income_category_id"}
                )
        }
)
@Data
public class IncomeOrExpenseSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 10)
    private String type;  // "income" or "expense"

    private Boolean linked;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "income_category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_income_category_id"))
    @JsonBackReference
    private IncomeOrExpenseCategory category;


}
