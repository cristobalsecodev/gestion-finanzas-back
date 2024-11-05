package com.gestionFinanzas.Troncales.IncomeOrExpense.RecurrenceDetails;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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
    private Integer frequency;

    @Column
    private LocalDate endDate;

    @Column
    private Integer occurrences;

    @ElementCollection
    @CollectionTable(name = "recurrence_days_of_week", joinColumns = @JoinColumn(name = "recurrence_id"))
    @Column(name = "day_of_week")
    private List<String> daysOfWeek;  // ["Monday", "Wednesday"], etc.

    @Column
    private Integer dayOfMonth;  // 1-31

}