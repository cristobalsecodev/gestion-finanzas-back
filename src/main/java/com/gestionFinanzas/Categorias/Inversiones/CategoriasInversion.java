package com.gestionFinanzas.Categorias.Inversiones;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipos_inversiones")
@Data
public class CategoriasInversion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

}
