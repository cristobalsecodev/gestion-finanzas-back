package Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ingresos")
public class Ingresos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipo_ingreso_id")
    private Integer tipo_ingreso_id;

}
