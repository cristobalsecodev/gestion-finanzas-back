package com.gestionFinanzas.Entities.Tipos;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos_ingresos")
public class TiposIngresos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

}
