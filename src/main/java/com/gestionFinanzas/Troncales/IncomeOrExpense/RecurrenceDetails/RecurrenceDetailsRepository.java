package com.gestionFinanzas.Troncales.IncomeOrExpense.RecurrenceDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurrenceDetailsRepository extends JpaRepository<RecurrenceDetails, Integer> {
}
