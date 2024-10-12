package com.gestionFinanzas.Rest.DatosMercadoValores.DTOs;

public enum TipoPeriodoENUM {
    DAILY("TIME_SERIES_DAILY"),
    WEEKLY("TIME_SERIES_WEEKLY"),
    MONTHLY("TIME_SERIES_MONTHLY");

    private String value;

    TipoPeriodoENUM(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
