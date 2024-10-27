package com.gestionFinanzas.Usuarios;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String surnames;

    @Column(name = "creation_date")
    private Date creationDate;

    private String password;

}
