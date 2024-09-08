package com.gestionFinanzas.Entities.Tipos;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos_gastos")
public class TiposGastos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

}
