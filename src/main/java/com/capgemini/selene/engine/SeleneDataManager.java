package com.capgemini.selene.engine;

import com.capgemini.selene.model.SeleneData;
import com.capgemini.selene.model.SeleneDataEnum;
import com.capgemini.selene.randomizer.DataFluctuationManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Should be replaced by JSON parsing...
public class SeleneDataManager {

    public static List<DataFluctuationManager> fluctuationManagers = new ArrayList<>();

    private SeleneDataManager() {
    }

    private final static Set<SeleneData> dataset = new HashSet<>();

    public static void generateData() {
        generateDataset();
        // Init fluctuationManager for each data
        for (SeleneData data : dataset) {
            fluctuationManagers.add(new DataFluctuationManager(data));
        }
        fluctuationManagers.sort(SeleneDataManager::compare);
    }

    private static void generateDataset() {
        // TODO : replace with Jackson parsing.
        for (SeleneDataEnum data : SeleneDataEnum.values())
            instanciateData(data);
    }

    // TODO : Lire via JSON, virer l'enum.
    private static void instanciateData(SeleneDataEnum data) {
        dataset.add(new SeleneData(data.getName(), data.getKind(), data.isPolluant(), data.getUnit(), data.getChemicalElement(), data.getMin(), data.getMax()));
    }

    // TODO : just call dfm.fluctuate and compute call data.fluctuate() directly in DFM.
    public static void fluctuate() {
        System.err.println("##############################NEXT DAY ##############################");
        fluctuationManagers.stream().forEach(DataFluctuationManager::nextDay);
    }

    public static int compare(DataFluctuationManager dfm1, DataFluctuationManager dfm2) {
        if (dfm1 == dfm2) return 0;
        if (dfm1 == null) return 1;
        if (dfm2 == null) return -1;
        SeleneData data1 = dfm1.getData();
        SeleneData data2 = dfm2.getData();
        if (data1.getKind() != data2.getKind()) return data1.getKind().compareTo(data2.getKind());
        return Integer.compare(data1.getRank(), data2.getRank());
    }
}
