package com.gestionFinanzas.Categories.Subcategories.Income;

import com.gestionFinanzas.Categories.Income.IncomeCategory;
import com.gestionFinanzas.Usuarios.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subcategorias_ingreso")
@Data
public class IncomeSubcategory {

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
    @JoinColumn(name = "income_category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_income_category_id"))
    private IncomeCategory incomeCategory;


}
