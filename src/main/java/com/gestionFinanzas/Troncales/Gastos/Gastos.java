package com.gestionFinanzas.Troncales.Gastos;

import com.gestionFinanzas.Usuarios.Usuarios;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gastos")
@Data
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "categoria_descripcion")
    private String categoriaDescripcion;

    @Column(name = "subcategoria_descripcion")
    private String subcategoriaDescripcion;

    @Column
    private String notas;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_usuario_id"))
    private Usuarios usuario;

}
