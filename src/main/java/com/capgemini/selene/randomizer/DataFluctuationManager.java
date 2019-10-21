package com.capgemini.selene.randomizer;

import java.util.Random;

import com.capgemini.selene.model.SeleneData;

// Todo : if increase keep having the same value, timeLeft should be generated with a smaller value, and increase should have a proportionnaly greater chance to be inverted.
public class DataFluctuationManager {
    private SeleneData data;
    private boolean increase;
    private int timeLeft;

    public DataFluctuationManager(SeleneData data){
        this.data = data;
        generateValues();
    }

    private void generateValues(){
        Random random = new Random();
        this.increase= random.nextBoolean();
        this.timeLeft = random.nextInt(20) + 1;
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
}
