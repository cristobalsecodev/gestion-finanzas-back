package com.gestionFinanzas.Categories.Investment;

import com.gestionFinanzas.Usuarios.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "investment_category")
@Data
public class InvestmentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

}
