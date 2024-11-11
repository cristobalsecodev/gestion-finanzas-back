package com.gestionFinanzas.Categories.Subcategories.Investment;

import com.gestionFinanzas.Categories.Investment.InvestmentCategory;
import com.gestionFinanzas.Usuarios.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "investment_subcategory")
@Data
public class InvestmentSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "investment_category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_investment_category_id"))
    private InvestmentCategory investmentCategory;


}
