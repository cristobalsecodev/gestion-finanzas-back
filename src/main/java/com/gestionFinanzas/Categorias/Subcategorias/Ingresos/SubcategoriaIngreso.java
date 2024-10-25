package com.gestionFinanzas.Categorias.Subcategorias.Ingresos;

import com.gestionFinanzas.Categorias.Ingresos.CategoriasIngreso;
import com.gestionFinanzas.Usuarios.Usuarios;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subcategorias_ingreso")
@Data
public class SubcategoriaIngreso {

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
    @JoinColumn(name = "categoria_ingreso_id", nullable = false, foreignKey = @ForeignKey(name = "fk_categoria_ingreso_id"))
    private CategoriasIngreso categoriaIngreso;


}
