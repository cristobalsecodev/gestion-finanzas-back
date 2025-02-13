package com.gestionFinanzas.Troncal.RecurrenceDetails;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "recurrence_details")
@Data
public class RecurrenceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String recurrenceType;  // "daily", "weekly", "monthly", "yearly"

    @Column(nullable = false)
    private Integer frequency; // Cada X días/semanas/meses/años

    @Column
    private LocalDate endDate; // Fecha de fin del proceso

    @Column
    private Integer occurrences; // Número de veces que ocurre la operación

}