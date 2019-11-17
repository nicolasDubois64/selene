package com.capgemini.selene.model;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class SeleneData {
    private static AtomicInteger rankProvider = new AtomicInteger();

    private final String name;
    private final Kind kind;
    private final boolean isPolluant;
    private final Unit unit;
    private String chemicalElement;
    private final float min;
    private final float max;
    private final float initialValue;
    private final int rank;

    private float value;

    // Represents min and max % of initial value by wich the value can vary.
    private float fluctuationMin;
    private float fluctuationMax;

    public float getInitialValue() {
        return initialValue;
    }

    public SeleneData(String name, Kind kind, boolean isPolluant, Unit unit, String chemicalElement, float min, float max) {
        this.name = name;
        this.kind = kind;
        this.isPolluant = isPolluant;
        this.unit = unit;
        this.chemicalElement = chemicalElement;
        this.min = min;
        this.max = max;
        this.value = min + ((max - min) / 2f);
        this.initialValue = this.value;
        // TODO : Should be calculated based on a percentage of the distance between initial value & min / max.
        computeFluctuation();
        rank = rankProvider.getAndIncrement();
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

    public boolean isPolluant() {
        return isPolluant;
    }

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

    public void setValue(int value) {
        this.value = value;
    }

    public float getFluctuationMin() {
        return fluctuationMin;
    }

    public float getFluctuationMax() {
        return fluctuationMax;
    }

    public int getRank() {
        return rank;
    }

    public void fluctuate(float v) {
        value += v;
    }

    @Override
    public String toString() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        StringBuilder sb = new StringBuilder(name);
        if (!("".equals(chemicalElement)))
            sb.append(" (" + chemicalElement + ")");
        sb.append(", " + kind.getName());
        sb.append(", [" + min + unit.getSymbol());
        sb.append(" < ");
        sb.append(df2.format(value));
        sb.append(" < ");
        sb.append(max + unit.getSymbol() + "]");
        return sb.toString();
    }

    public String debugString() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        StringBuilder sb = new StringBuilder(name + " -> ");
        sb.append("min : " + df2.format(min) + "; ")
                .append("max : " + df2.format(max) + "; ")
                .append("initialValue : " + df2.format(initialValue) + "; ")
                .append("value : " + df2.format(value) + "; ")
                .append("flucMin : " + df2.format(fluctuationMin) + "; ")
                .append("flucMax : " + df2.format(fluctuationMax) + "; ");

        return sb.toString();
    }

    private void computeFluctuation() {
        fluctuationMin = ((max - initialValue) * (float) (isPolluant ? 5 : 3) / 100f);
        fluctuationMax = ((max - initialValue) * (float) (isPolluant ? 7 : 5) / 100f);
    }
}
