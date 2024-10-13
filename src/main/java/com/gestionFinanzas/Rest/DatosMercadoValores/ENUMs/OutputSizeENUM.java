package com.gestionFinanzas.Rest.DatosMercadoValores.ENUMs;

public enum OutputSizeENUM {
    COMPACT("compact"),
    FULL("full");

    private String value;

    OutputSizeENUM(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
