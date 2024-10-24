package com.gestionFinanzas.Categorias.Gastos;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipos_gastos")
@Data
public class CategoriasGasto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

}
