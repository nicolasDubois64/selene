package com.capgemini.selene.model;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class SeleneData {
    private static AtomicInteger rankProvider = new AtomicInteger();

    private  String name;
    private  Kind kind;
    private  boolean polluant;
    private  Unit unit;
    private String chemicalElement;
    private  float min;
    private  float max;
    private  float initialValue;
    private  int rank;

    private float value;

    // Represents min and max % of initial value by wich the value can vary.
    private float fluctuationMin;
    private float fluctuationMax;

    public float getInitialValue() {
        return initialValue;
    }

    public SeleneData(){}

    public SeleneData(String name, Kind kind, boolean polluant, Unit unit, String chemicalElement, float min, float max) {
        this.name = name;
        this.kind = kind;
        this.polluant = polluant;
        this.unit = unit;
        this.chemicalElement = chemicalElement;
        this.min = min;
        this.max = max;
        this.value = isPercentagePolluant() ? 0 : min + ((max - min) / 2f);
        this.initialValue = this.value;
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
        return polluant;
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

    public void addValue(float v) {
        if (!(value + v < 0 || (value + v > 100 && unit == Unit.PERCENTAGE)))
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

    public boolean isPercentagePolluant() {
        return Unit.PERCENTAGE.equals(unit) && polluant;
    }

    private void computeFluctuation() {
        if (!isPercentagePolluant()) {
            fluctuationMin = ((max - initialValue) * (float) (polluant ? 5 : 3) / 100f);
            fluctuationMax = ((max - initialValue) * (float) (polluant ? 7 : 5) / 100f);
        } else {
            fluctuationMin = (max / (100 + max)) * 50f / 100f;
            fluctuationMax = (max / (100 + max)) * 75f / 100f;
        }
    }

    public void initValue(){
        this.value = isPercentagePolluant() ? 0 : min + ((max - min) / 2f);
        this.initialValue = this.value;
        computeFluctuation();
        rank = rankProvider.getAndIncrement();
    }

}
