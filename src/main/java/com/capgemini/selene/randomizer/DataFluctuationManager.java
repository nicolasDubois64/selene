package com.capgemini.selene.randomizer;

import java.text.DecimalFormat;
import java.util.Random;

import com.capgemini.selene.SeleneApplication;
import com.capgemini.selene.model.SeleneData;

// Todo : if increase keep having the same value, timeLeft should be generated with a smaller value, and increase should have a proportionnaly greater chance to be inverted.
public class DataFluctuationManager {
    private static int NEW_LESSER = 0;
    private static int NEW_EQUAL = 0;
    private static int NEW_GREATER = 0;

    private SeleneData data;
    private boolean increase;
    private int timeLeft = -1;
    private int lastTimeLeftGenerated = 0;

    public DataFluctuationManager(SeleneData data){
        this.data = data;
        Random random = new Random();
        this.timeLeft = random.nextInt(20) + 1;
        this.lastTimeLeftGenerated = this.timeLeft;
        this.increase= random.nextBoolean();
    }

    private void generateValues(){
        Random random = new Random();
        boolean oldIncrease = this.increase;
        this.increase= random.nextBoolean();
        // We generated the same fluctuation direction. The chance to have lower timeLeft value should be increased.
        if(oldIncrease == increase) {
            int shift = (int) Math.ceil(1d / (20 - (lastTimeLeftGenerated - 1)) * 20);
            this.timeLeft = random.nextInt(shift) + 1;
        }
        else
            this.timeLeft = random.nextInt(20) + 1;
        if(this.timeLeft < this.lastTimeLeftGenerated) {NEW_LESSER++;}
        if(this.timeLeft == this.lastTimeLeftGenerated) {NEW_EQUAL++;}
        if(this.timeLeft > this.lastTimeLeftGenerated) {NEW_GREATER++;}
        this.lastTimeLeftGenerated = this.timeLeft;
    }

    public int next(){
        if(timeLeft > 0)
            return --timeLeft;
        generateValues();
        return timeLeft;
    }

    public SeleneData getData(){
        return data;
    }

    @Override
    public String toString() {
        return data.getName() + " : " + (increase ? "increasing" : "decreasing") + " during " + timeLeft + " time unit(s).";
    }

    public static String getStats(){
        int total = NEW_LESSER + NEW_EQUAL + NEW_GREATER;
        StringBuilder sb = new StringBuilder();
        DecimalFormat df2 = new DecimalFormat("#.##");
        sb.append("New value < old value : " + NEW_LESSER + "(" + df2.format((double)NEW_LESSER / total * 100)  + "%)");
        sb.append("<br />");
        sb.append("New value == old value : " + NEW_EQUAL + "(" + df2.format((double)NEW_EQUAL / total * 100)  + "%)");
        sb.append("<br />");
        sb.append("New value > old value : " + NEW_GREATER + "(" + df2.format((double)NEW_GREATER / total * 100)  + "%)");

        return sb.toString();
    }

    private String debugString(int shift){
        StringBuilder sb = new StringBuilder("Data ");
        sb.append(data.getName());
        sb.append("[ ");
        sb.append("Increase=" + increase + "; ");
        sb.append("Old timeleft=" + lastTimeLeftGenerated + "; ");
        sb.append("New timeleft=" + timeLeft + "; ");
        sb.append("Shift=" + shift + "; ");
        sb.append(" ]");
        return sb.toString();
    }
}
