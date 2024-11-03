package com.gestionFinanzas.Auth.ENUMs;

public enum OneTimeUrlTypeEnum {

    RESET_PASS("RESET_PASS");

    private final String name;

    OneTimeUrlTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
