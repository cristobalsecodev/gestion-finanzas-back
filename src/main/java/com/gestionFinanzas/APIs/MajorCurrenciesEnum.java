package com.gestionFinanzas.APIs;

public enum MajorCurrenciesEnum {
    USD("US Dollar"),
    EUR("Euro"),
    JPY("Japanese Yen"),
    GBP("British Pound"),
    CHF("Swiss Franc"),
    CAD("Canadian Dollar"),
    AUD("Australian Dollar"),
    CNY("Chinese Renminbi"),
    NZD("New Zealand Dollar"),
    INR("Indian Rupee");

    private final String name;

    MajorCurrenciesEnum(String name) {
        this.name = name;
    }

    public String getCodigo() {
        return this.name();
    }

    public String getName() {
        return name;
    }
}
