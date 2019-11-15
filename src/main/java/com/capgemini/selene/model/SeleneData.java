package com.capgemini.selene.model;

import java.text.DecimalFormat;

public class SeleneData {
    private final String name;
    private final Kind kind;
    private final boolean isPolluant;
    private final Unit unit;
    private String chemicalElement;
    private final float min;
    private final float max;
    private final float initialValue;

    private float value;

    // Represents min and max % of initial value by wich the value can vary.
    private int fluctuationMin;
    private int fluctuationMax;

    public float getInitialValue() {
        return initialValue;
    }

    public SeleneData(String name, Kind kind, boolean isPolluant,Unit unit, String chemicalElement, float min, float max){
        this.name = name;
        this.kind = kind;
        this.isPolluant = isPolluant;
        this.unit = unit;
        this.chemicalElement = chemicalElement;
        this.min = min;
        this.max = max;
        this.value = (max - min) / 2f;
        this.initialValue = this.value;
        // TODO : discuss. Should be calculated based on the distance between initial value & min / max.
        fluctuationMin = 5;
        fluctuationMax = 7;
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

    public boolean isPolluant() { return isPolluant; }

    public String getChemicalElement() {
        return chemicalElement;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getValue() {
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getFluctuationMin() {
        return fluctuationMin;
    }

    public int getFluctuationMax() {
        return fluctuationMax;
    }

    public void setFluctuationMin(int fluctuationMin) {
        this.fluctuationMin = fluctuationMin;
    }

    public void setFluctuationMax(int fluctuationMax) {
        this.fluctuationMax = fluctuationMax;
    }



    public void fluctuate(float v) {
        value += v;
    }

    @Override
    public String toString() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        StringBuilder sb = new StringBuilder(name);
        if(!("".equals(chemicalElement)))
            sb.append(" (" + chemicalElement + ")");
        sb.append(", " + kind.getName());
        sb.append(", [" + min + unit.getSymbol());
        sb.append(" < ");
        sb.append(df2.format(value));
        sb.append(" < ");
        sb.append(max + unit.getSymbol() + "]");
        return sb.toString();
    }
}
