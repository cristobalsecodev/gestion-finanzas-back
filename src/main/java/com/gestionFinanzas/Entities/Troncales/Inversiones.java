package com.gestionFinanzas.Entities.Troncales;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "inversiones")
public class Inversiones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fecha_compra")
    private Date fecha_compra;

    @Column(name = "cantidad_compra")
    private Integer cantidad_compra;

    @Column(name = "fecha_venta")
    private Date fecha_venta;

    @Column(name = "cantidad_venta")
    private Integer cantidad_venta;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipo_inversion_id")
    private Integer tipo_inversion_id;


}
