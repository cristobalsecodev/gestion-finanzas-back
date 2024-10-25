package com.gestionFinanzas.Troncales.Inversiones;

import com.gestionFinanzas.Usuarios.Usuarios;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "inversiones")
@Data
public class Inversiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @Column(name = "cantidad_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidadCompra;

    @Column(name = "categoria_descripcion")
    private String categoriaDescripcion;

    @Column(name = "subcategoria_descripcion")
    private String subcategoriaDescripcion;

    @Column
    private String notas;

    @Column(name = "cantidad_venta", precision = 10, scale = 2)
    private BigDecimal cantidadVenta;

    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_usuario_id"))
    private Usuarios usuario;

}
