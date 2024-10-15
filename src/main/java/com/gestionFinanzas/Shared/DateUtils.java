package com.gestionFinanzas.Shared;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static String dateToString(Date date, String formato) {
        if(date == null || formato.isEmpty()) {
            throw new IllegalArgumentException("La Fecha y el formato son obligatorios");
        }

        // Convertimos Date a LocalDate
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Definimos el formato
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);

        return localDate.format(formatter);
    }
}
