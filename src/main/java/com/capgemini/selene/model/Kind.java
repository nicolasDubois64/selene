package com.capgemini.selene.model;

public enum Kind {
    WATER("Eau"),
    AIR("Air"),
    FOOD("Nourriture"),
    ENERGY("Énergie"),
    TEMPERATURE("Température"),
    PRESSURE("Pression atmosphérique");

    private final String name;


    Kind(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
