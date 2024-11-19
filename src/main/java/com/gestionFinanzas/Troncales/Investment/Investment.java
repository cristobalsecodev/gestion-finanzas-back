package com.gestionFinanzas.Troncales.Investment;

import com.gestionFinanzas.Usuarios.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "investment")
@Data
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "purchase_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal purchaseAmount;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "subcategory", length = 50)
    private String subcategory;

    @Column(name = "exchange_rate_to_usd", nullable = false, precision = 10, scale = 4)
    private BigDecimal exchangeRateToUsd;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "sale_amount", precision = 15, scale = 2)
    private BigDecimal saleAmount;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

}
