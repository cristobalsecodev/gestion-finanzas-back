package com.gestionFinanzas.Categorias.Ingresos;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipos_ingresos")
@Data
public class CategoriasIngreso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

}
