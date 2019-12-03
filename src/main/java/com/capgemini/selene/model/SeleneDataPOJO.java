package com.capgemini.selene.model;

public class SeleneDataPOJO {

    private final String name;
    private final Kind kind;
    private final Unit unit;
    private final String chemicalElement;
    private final float value;

    public SeleneDataPOJO(SeleneData data){
        name = data.getName();
        kind = data.getKind();
        unit = data.getUnit();
        chemicalElement = data.getChemicalElement();
        value = data.getValue();
    }

    public String getName() {
        return name;
    }

    public Kind getKind() {
        return kind;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getChemicalElement() {
        return chemicalElement;
    }

    public float getValue() {
        return value;
    }
}
