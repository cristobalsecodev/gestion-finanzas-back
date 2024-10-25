package com.gestionFinanzas.Categorias.Gastos;

import com.gestionFinanzas.Usuarios.Usuarios;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categorias_gasto")
@Data
public class CategoriasGasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_usuario_id"))
    private Usuarios usuario;

}
