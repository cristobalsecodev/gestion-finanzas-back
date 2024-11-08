package com.gestionFinanzas.Troncales.IncomeOrExpense.RecurrenceDetails;

import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.IllegalArgumentException;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.ResourceConflictException;
import com.gestionFinanzas.Troncales.IncomeOrExpense.IncomeOrExpense;
import com.gestionFinanzas.Troncales.IncomeOrExpense.IncomeOrExpenseRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecurrenceDetailsService {

    private final IncomeOrExpenseRepository incomeOrExpenseRepository;

    private final RecurrenceDetailsRepository recurrenceDetailsRepository;

    public RecurrenceDetailsService(
            IncomeOrExpenseRepository incomeOrExpenseRepository,
            RecurrenceDetailsRepository recurrenceDetailsRepository
    ) {
        this.incomeOrExpenseRepository = incomeOrExpenseRepository;
        this.recurrenceDetailsRepository = recurrenceDetailsRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Ejecuta la tarea cada día a medianoche
    public void generateRecurringEntries() {

        LocalDate today = LocalDate.now();

        // Devolvemos las recurrencias activas
        List<RecurrenceDetails> activeRecurrences = recurrenceDetailsRepository.findActiveRecurrences();

        for(RecurrenceDetails recurrence : activeRecurrences) {

            // Devolvemos el último registro de ingreso o gasto relacionado a su recurrencia
            IncomeOrExpense lastEntry = incomeOrExpenseRepository.findByRecurrenceId(recurrence.getId());

            if(lastEntry != null) {

                // Verificamos si el día coincide con la fecha del próximo ingreso / gasto
                Boolean isVerified = verifyDay(today, recurrence, lastEntry);

                if(isVerified) {

                    // Creamos el nuevo objeto de ingreso / gasto
                    IncomeOrExpense newEntry = buildIncomeOrExpense(recurrence, today, lastEntry);

                    // Guardamos la nueva instancia
                    incomeOrExpenseRepository.save(newEntry);

                }

            } else {
                throw new ResourceConflictException("The recurrence does not have a related income or expense record");
            }

        }

    }

    private static IncomeOrExpense buildIncomeOrExpense(RecurrenceDetails recurrence, LocalDate today, IncomeOrExpense lastEntry) {

        IncomeOrExpense newEntry = new IncomeOrExpense();

        newEntry.setDate(today);
        newEntry.setCategory(lastEntry.getCategory());
        newEntry.setSubCategory(lastEntry.getSubCategory());
        newEntry.setAmount(lastEntry.getAmount());
        newEntry.setCurrency(lastEntry.getCurrency());
        newEntry.setType(lastEntry.getType());
        newEntry.setNotes(lastEntry.getNotes());
        newEntry.setRecurrenceDetails(recurrence);

        return newEntry;
    }

    private Boolean verifyDay(LocalDate today, RecurrenceDetails recurrence, IncomeOrExpense lastEntry) {

        // Fecha base tomando como referencia el último registro de ingreso / gasto
        LocalDate baseDate = lastEntry.getDate();

        // Calcula la próxima fecha según el tipo de recurrencia y su frecuencia
        LocalDate nextDate =
            switch (recurrence.getRecurrenceType().toLowerCase()) {
                case "daily" -> baseDate.plusDays(recurrence.getFrequency());
                case "weekly" -> baseDate.plusWeeks(recurrence.getFrequency());
                case "monthly" -> baseDate.plusMonths(recurrence.getFrequency());
                case "yearly" -> baseDate.plusYears(recurrence.getFrequency());
                default ->
                    throw new IllegalArgumentException("Invalid recurrence type: " + recurrence.getRecurrenceType());
        };

        // Verifica si la próxima fecha está fuera del rango de endDate
        if(recurrence.getEndDate() != null && nextDate.isAfter(recurrence.getEndDate())) {

            finishRecurrence(today, recurrence);

            return false;

        }

        // Verifica si el límite de ocurrencias ha sido alcanzado
        if(recurrence.getOccurrences() != null) {

            Long count = incomeOrExpenseRepository.countByRecurrenceId(recurrence.getId());

            if(count >= recurrence.getOccurrences()) {

                finishRecurrence(today, recurrence);

                return false;

            }

        }

        // Confirmamos si la fecha calculada es la de hoy
        return nextDate.isEqual(today);

    }

    private void finishRecurrence(LocalDate date, RecurrenceDetails recurrence) {

        // Seteamos la fecha y cerramos la recurrencia
        recurrence.setEndDate(date);

        recurrenceDetailsRepository.save(recurrence);

    }

}
