package com.gestionFinanzas.Categories.Subcategories.Investment;

import com.gestionFinanzas.Categories.Investment.InvestmentCategory;
import com.gestionFinanzas.Usuarios.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subcategorias_inversion")
@Data
public class InvestmentSubcategory {

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
    @JoinColumn(name = "investment_category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_investment_category_id"))
    private InvestmentCategory investmentCategory;


}
