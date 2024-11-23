package com.gestionFinanzas.Troncales.IncomeOrExpense.RecurrenceDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurrenceDetailsRepository extends JpaRepository<RecurrenceDetails, Long> {

    @Query(value = "SELECT r FROM RecurrenceDetails r WHERE (r.endDate IS NULL OR r.endDate >= CURRENT_DATE)")
    List<RecurrenceDetails> findActiveRecurrences();

}
