package com.capgemini.selene.engine;

import com.capgemini.selene.model.SeleneData;
import com.capgemini.selene.model.SeleneDataEnum;
import com.capgemini.selene.randomizer.DataFluctuationManager;

import java.util.*;

// Should be replaced by JSON parsing...
public class SeleneDataManager {

    public static List<DataFluctuationManager> fluctuationManagers = new ArrayList<>();

    private SeleneDataManager(){}
    private final static Set<SeleneData> dataset = new HashSet<>();

    public static void generateData(){
        generateDataset();
        // Init fluctuationManager for each data
        for(SeleneData data : dataset){
            fluctuationManagers.add(new DataFluctuationManager(data));
        }
    }

    private static void generateDataset() {
        // TODO : replace with Jackson parsing.
        for (SeleneDataEnum data: SeleneDataEnum.values())
            instanciateData(data);
    }

    private static void instanciateData(SeleneDataEnum data) {
        dataset.add(new SeleneData(data.getName(), data.getKind(), data.getUnit(), data.getChemicalElement(), data.getMin(), data.getMax()));
    }

    public static void fluctuate() {
        Random rand = new Random();
        for(DataFluctuationManager dfm : fluctuationManagers){
            dfm.next();
            SeleneData data = dfm.getData();
            int i = rand.nextInt(data.getFluctuationMax() - data.getFluctuationMin() + data.getFluctuationMin());
            float delta = data.getInitialValue() * (float)i / 100f;
            data.fluctuate(dfm.isIncrease() ? delta : -delta);

        }
    }
}
