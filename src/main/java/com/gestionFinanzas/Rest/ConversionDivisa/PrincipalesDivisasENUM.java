package com.gestionFinanzas.Rest.ConversionDivisa;

public enum PrincipalesDivisasENUM {
    USD("Dólar estadounidense"),
    EUR("Euro"),
    JPY("Yen japonés"),
    GBP("Libra esterlina"),
    CHF("Franco suizo"),
    CAD("Dólar canadiense"),
    AUD("Dólar australiano"),
    CNY("Renminbi chino"),
    NZD("Dólar neozelandés"),
    INR("Rupia india");

    private final String nombre;

    PrincipalesDivisasENUM(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return this.name();
    }

    public String getNombre() {
        return nombre;
    }
}
