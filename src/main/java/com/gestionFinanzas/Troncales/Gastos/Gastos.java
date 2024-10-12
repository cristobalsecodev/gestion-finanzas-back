package com.gestionFinanzas.Troncales.Gastos;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "gastos")
@Data
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipo_gasto_id")
    private Integer tipo_gasto_id;

}
