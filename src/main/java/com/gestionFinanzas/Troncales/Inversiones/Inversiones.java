package com.gestionFinanzas.Troncales.Inversiones;

import com.gestionFinanzas.Extra.RendimientoAnualInversiones;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inversiones")
@Data
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

    @OneToMany(mappedBy = "inversion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RendimientoAnualInversiones> rendimientosAnuales;


}
