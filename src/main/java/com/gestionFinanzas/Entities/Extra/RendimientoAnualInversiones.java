package com.gestionFinanzas.Entities.Extra;

import com.gestionFinanzas.Entities.Troncales.Inversiones;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rendimiento_anual_inversiones")
@Data
public class RendimientoAnualInversiones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "rendimiento")
    private Double rendimiento;

    @ManyToOne
    @JoinColumn(name = "inversion_id")
    private Inversiones inversion;

}
