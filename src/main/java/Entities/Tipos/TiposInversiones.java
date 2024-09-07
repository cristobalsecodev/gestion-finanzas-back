package Entities.Tipos;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_inversiones")
public class TiposInversiones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

}
