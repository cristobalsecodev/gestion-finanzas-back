package com.gestionFinanzas.Categorias.Subcategorias.Gastos;

import com.gestionFinanzas.Categorias.Gastos.CategoriasGasto;
import com.gestionFinanzas.Usuarios.Usuarios;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subcategorias_gasto")
@Data
public class SubcategoriaGasto {

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

    @ManyToOne
    @JoinColumn(name = "categoria_gasto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_categoria_gasto_id"))
    private CategoriasGasto categoriaGasto;

}
