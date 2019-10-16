package com.capgemini.selene.selene.engine;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class SeleneEngine {
    private static AtomicInteger dayIndex = new AtomicInteger(0);
    private static LocalDate date = LocalDate.now();

    public static void nextDay(){
        // Advance time unit.
        date = date.plusDays(1);
        dayIndex.addAndGet(1);

        // Flutuate
        SeleneDataManager.fluctuate();

    }

    public static AtomicInteger getDayIndex(){
        return dayIndex;
    }

    public static LocalDate getDate(){
        return date;
    }
}
