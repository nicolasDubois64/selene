package com.capgemini.selene.randomizer;

import com.capgemini.selene.model.SeleneData;
import com.capgemini.selene.model.Unit;

import java.util.Random;

public class DataFluctuationManager {

    private SeleneData data;
    private boolean increase;
    private int timeLeft;
    private int lastTimeLeftGenerated;
    private float nonPolluantMin;
    private float nonPolluantMax;
    private float mediumValue;
    private float polluantDelta;
    private FluctuationMode mode;
    private float straightTarget = 0;
    private float straightStep = 0;

    private enum FluctuationMode {
        STRAIGHT,
        RANDOM,
        PATTERN
    }

    public DataFluctuationManager(SeleneData data) {
        this.data = data;
        Random random = new Random();
        this.timeLeft = random.nextInt(20) + 1;
        this.lastTimeLeftGenerated = this.timeLeft;
        this.increase = (data.isPolluant() && Unit.PERCENTAGE.equals(data.getUnit())) ? true : random.nextBoolean();
        mediumValue = data.getInitialValue();
        if (data.isPolluant())
            polluantDelta = ((data.getMax() - data.getMin()) / 100f) * 5f;
        else {
            nonPolluantMin = data.getMin() + (((data.getMax() - data.getMin()) * (random.nextInt(47 - 41) + 41)) / 100f);
            nonPolluantMax = data.getMax() - (((data.getMax() - data.getMin()) * (random.nextInt(47 - 41) + 41)) / 100f);
        }
        mode = FluctuationMode.RANDOM;
    }

    public void nextDay() {
        if (data.getUnit() == Unit.PERCENTAGE) {
            if (Double.compare(data.getValue(), 100f) >= 0) return;
            if (data.isPolluant()) {
                addRandomValue();
                return;
            }
        }
        switch (mode) {
            case RANDOM:
                doRandom();
                break;
            case STRAIGHT:
                doStraight();
                break;
            case PATTERN:
//                doPattern();
                break;
            default:
                return;
        }
    }

    public SeleneData getData() {
        return data;
    }

    @Override
    public String toString() {
        return (increase ? "↑" : "↓") + " during " + timeLeft + " days";
    }

    // RANDOM
    private void doRandom() {
        if (timeLeft == 0)
            fluctuate();
        addRandomValue();
        if (timeLeft > 0) timeLeft--;
    }

    /**
     * Generate a new fluctuation (up or down and time left).
     */
    private void fluctuate() {
        if (data.isPolluant())
            fluctuatePolluant();
        else
            fluctuateNonPolluant();

    }

    /**
     * Polluants should keep growing up overall but with various fluctuations.
     */
    private void fluctuatePolluant() {
        mediumValue += polluantDelta;
        if (!increase && data.getValue() < mediumValue - (mediumValue * data.getFluctuationMax()))
            setStraight();
        else
            generateIncreaseAndTimeLeft();
    }

    /**
     * Non Polluants just addValue between two random values by a little amount each time.
     */
    private void fluctuateNonPolluant() {
        if (data.getValue() < nonPolluantMin || data.getValue() > nonPolluantMax)
            setStraight();
        else
            generateIncreaseAndTimeLeft();
    }

    /**
     * If the timeleft == 0, a new one is generated. If the same direction is randomized, the timeleft will be shorter and shorter until the opposite direction is picked.
     */
    private void generateIncreaseAndTimeLeft() {
        Random random = new Random();
        boolean oldIncrease = increase;
        this.increase = random.nextBoolean();
        // We generated the same fluctuation direction. The chance to have lower timeLeft value should be increased.
        if (oldIncrease == increase) {
            int shift = (int) Math.ceil(1d / (20 - (lastTimeLeftGenerated - 1)) * 20);
            timeLeft = random.nextInt(shift) + 1;
        } else
            timeLeft = random.nextInt(20) + 1;
        lastTimeLeftGenerated = timeLeft;
    }

    /**
     * Generate a new value for the data.
     */
    private void addRandomValue() {
        Random rand = new Random();
        float i = rand.nextFloat() * (data.getFluctuationMax() - data.getFluctuationMin()) + data.getFluctuationMin();
//        debugUpdateDataValue(i);
        data.addValue(increase ? i : -i);
    }

    private void setStraight() {
        System.err.println(data.getName() + " straight mode");
        mode = FluctuationMode.STRAIGHT;
        //        System.err.println(debugString());
        Random rand = new Random();
        increase = !increase;
        int delta = rand.nextInt(6) - 3;
        straightTarget = mediumValue + (((data.getMax() - data.getMin()) * (float) delta) / 100f);
        straightStep = (Math.abs(data.getValue() - mediumValue) * ((float) rand.nextInt(25 - 10) + 10)) / 100f;
        if (!data.isPolluant() && data.getValue() > nonPolluantMax) straightStep = -straightStep;
        timeLeft = 0;
        lastTimeLeftGenerated = 0;
    }

    // STRAIGHT

    private void doStraight() {
        data.addValue(straightStep);
        if ((increase && data.getValue() > straightTarget) || (!increase && data.getValue() < straightTarget)) {
            increase = !increase;
            setRandom();
        }
    }

    private void setRandom() {
        System.err.println(data.getName() + " random mode");
        straightTarget = 0;
        straightStep = 0;
        mode = FluctuationMode.RANDOM;
        timeLeft = 0;
    }

    // TODO : PATTERN

    // DEBUG


    public void reset() {
        mediumValue = data.getInitialValue();
    }

    private String debugString() {
        return data.debugString()
                + "mediumValue : " + mediumValue
                //+ ";  delta : " + delta
                //+ "; i : " + i
                + "; npolMin : " + nonPolluantMin
                + "; npolMax : " + nonPolluantMax
                + "]";
    }

    private void debugUpdateDataValue(float i) {
        if (data.getName() == "Calcium")
            System.err.println(data.getName()
                    + data.debugString()
                    + "mediumValue : " + mediumValue
                    //+ ";  delta : " + delta
                    + "; i : " + i
                    + "; npolMin : " + nonPolluantMin
                    + "; npolMax : " + nonPolluantMax
                    + "]");
    }
}
