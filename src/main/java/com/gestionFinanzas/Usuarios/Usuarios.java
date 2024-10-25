package com.gestionFinanzas.Usuarios;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "usuarios")
@Data
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

}
