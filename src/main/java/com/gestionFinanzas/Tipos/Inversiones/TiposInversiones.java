package com.gestionFinanzas.Tipos.Inversiones;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipos_inversiones")
@Data
public class TiposInversiones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

}
