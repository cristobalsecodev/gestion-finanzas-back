package com.gestionFinanzas.Shared.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {

    public static String localDateFormatter(LocalDate date, String formato) {

        if(date == null || formato.isEmpty()) {

            throw new IllegalArgumentException("La Fecha y el formato son obligatorios");

        }

        // Definimos el formato
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);

        // Convertimos la fecha a string
        return date.format(formatter);

    }
}
