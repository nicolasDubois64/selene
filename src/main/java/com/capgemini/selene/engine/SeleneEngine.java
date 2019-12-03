package com.capgemini.selene.engine;

import com.capgemini.selene.model.Kind;
import com.capgemini.selene.randomizer.DataFluctuationManager;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class SeleneEngine {
    private static AtomicInteger dayIndex = new AtomicInteger(0);
    private static LocalDate date = LocalDate.now();

    public static void nextDay() {
        // Advance time unit.
        date = date.plusDays(1);
        dayIndex.addAndGet(1);

        // Flutuate
        SeleneDataManager.fluctuate();

    }

    public static void purge(Kind kind) {
        SeleneDataManager.fluctuationManagers.stream().filter(dfm -> kind.equals(dfm.getData().getKind()) && dfm.canPurge()).forEach(DataFluctuationManager::purge);
    }

    public static AtomicInteger getDay() {
        return dayIndex;
    }

    public static LocalDate getDate() {
        return date;
    }
}
