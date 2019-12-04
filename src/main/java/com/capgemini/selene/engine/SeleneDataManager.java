package com.capgemini.selene.engine;

import com.capgemini.selene.model.SeleneData;
import com.capgemini.selene.parser.SeleneDataParser;
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
        dataset.addAll(SeleneDataParser.getSeleneData());
        // Init fluctuationManager for each data
        for (SeleneData data : dataset) {
            fluctuationManagers.add(new DataFluctuationManager(data));
        }
        fluctuationManagers.sort(SeleneDataManager::compare);
    }

    // TODO : just call dfm.addValue and compute call data.addValue() directly in DFM.
    public static void fluctuate() {
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
