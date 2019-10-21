package com.capgemini.selene.engine;

import com.capgemini.selene.model.SeleneData;
import com.capgemini.selene.randomizer.DataFluctuationManager;

import java.util.ArrayList;
import java.util.List;

// Should be replaced by JSON parsing...
public class SeleneDataManager {

    public static List<DataFluctuationManager> fluctuationManagers = new ArrayList<>();

    private SeleneDataManager(){}

    public static void generateData(){
        // Init fluctuationManager for each data
        for(SeleneData data : SeleneData.values()){
            fluctuationManagers.add(new DataFluctuationManager(data));
        }
    }

    public static void fluctuate() {
        for(DataFluctuationManager dfm : fluctuationManagers){
            // New values should be generated here
            //dfm.fluctuate();
            dfm.next();
        }
    }
}
