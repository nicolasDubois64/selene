package com.capgemini.selene.model;

public enum Unit {
    MILLIGRAM_PER_LITER ("mg/L"),
    MICROGRAM_PER_LITER("µg/L"),
    COLONY_FORMING_UNIT_PER_LITER("UFC/L"),
    COLONY_FORMING_UNIT_PER_KILOGRAM("UFC/kg"),
    PERCENTAGE("%"),
    MEGAWATT_HOUR("MWh"),
    CELSIUS_DEGREE("°C"),
    ATMOSPHERE("atm");

    private final String symbol;

    Unit(String symbol){
        this.symbol = symbol;
    }

    String getSymbol(){
        return symbol;
    }
}
