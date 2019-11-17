package com.capgemini.selene.randomizer;

import com.capgemini.selene.model.SeleneData;

import java.util.Random;

// Todo : if increase keep having the same value, timeLeft should be generated with a smaller value, and increase should have a proportionnaly greater chance to be inverted.
public class DataFluctuationManager {

    private SeleneData data;
    private boolean increase;
    private int timeLeft = -1;
    private int lastTimeLeftGenerated = 0;
    private float nonPolluantMin;
    private float nonPolluantMax;
    private float mediumValue;
    private float polluantDelta;

    public DataFluctuationManager(SeleneData data) {
        this.data = data;
        Random random = new Random();
        this.timeLeft = random.nextInt(20) + 1;
        this.lastTimeLeftGenerated = this.timeLeft;
        this.increase = random.nextBoolean();
        mediumValue = data.getInitialValue();
        if (data.isPolluant())
            polluantDelta = ((data.getMax() - data.getMin()) / 100f) * 5f;
        else {
            Random rand = new Random();
            nonPolluantMin = data.getMin() + (((data.getInitialValue() - data.getMin()) * rand.nextInt(25 - 18) + 18) / 100f);
            nonPolluantMax = data.getMax() - (((data.getMax() - data.getInitialValue()) * rand.nextInt(25 - 18) + 18) / 100f);
        }

    }

    public int nextDay() {
        if (timeLeft == 0)
            fluctuate();
        updateDataValue();
        return timeLeft > 0 ? --timeLeft : timeLeft;
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
            invert();
    }

    /**
     * Non Polluants just fluctuate between two random values by a little amount each time.
     */
    private void fluctuateNonPolluant() {
        if (data.getValue() < nonPolluantMin || data.getValue() > nonPolluantMax)
            invert();
        else
            generateIncreaseAndTimeLeft();
    }

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

    private void invert() {
        System.err.println(debugString());
        Random rand = new Random();
        increase = !increase;
        timeLeft = lastTimeLeftGenerated == 20 ? rand.nextInt(20) + 1 : rand.nextInt(20 - lastTimeLeftGenerated) + lastTimeLeftGenerated;
        lastTimeLeftGenerated = timeLeft;
    }

    private void updateDataValue() {
        Random rand = new Random();
        float i = rand.nextFloat() * (data.getFluctuationMax() - data.getFluctuationMin()) + data.getFluctuationMin();
       /* if (data.getName() == "Calcium")
            System.err.println(data.getName()
                    + data.debugString()
                    + "mediumValue : " + mediumValue
                    //+ ";  delta : " + delta
                    + "; i : " + i
                    + "; npolMin : " + nonPolluantMin
                    + "; npolMax : " + nonPolluantMax
                    + "]");*/
        data.fluctuate(increase ? i : -i);

    }

    public SeleneData getData() {
        return data;
    }

    @Override
    public String toString() {
        return (increase ? "↑" : "↓") + " during " + timeLeft + " days";
    }

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
}
