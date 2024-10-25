package com.gestionFinanzas.Categorias.Subcategorias.Inversiones;

import com.gestionFinanzas.Categorias.Inversiones.CategoriasInversion;
import com.gestionFinanzas.Usuarios.Usuarios;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subcategorias_inversion")
@Data
public class SubcategoriaInversion {

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
    @JoinColumn(name = "categoria_inversion_id", nullable = false, foreignKey = @ForeignKey(name = "fk_categoria_inversion_id"))
    private CategoriasInversion categoriasInversion;


}
