package com.capgemini.selene.selene.model;

public enum Kind {
    WATER("Eau"),
    AIR("Air"),
    ENERGY("Énergie"),
    FOOD("Nourriture"),
    TEMPERATURE("Température"),
    PRESSURE("Pression atmosphérique");

    private final String name;

    Kind(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
