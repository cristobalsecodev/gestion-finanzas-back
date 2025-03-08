package com.gestionFinanzas.Categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestionFinanzas.Categories.Subcategories.IncomeOrExpenseSubcategory;
import com.gestionFinanzas.Usuarios.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "income_or_expense_category",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_name_type_user",
                        columnNames = {"name", "type", "user_id"}
                )
        }
)
@Data
public class IncomeOrExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 10)
    private String type;  // "income" or "expense"

    @Column(nullable = false, length = 7)
    private String color;

    private Boolean linked;

    private Boolean active;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<IncomeOrExpenseSubcategory> subcategories = new ArrayList<>();

}
