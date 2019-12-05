package com.capgemini.selene.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

// POJO to be sent via JSON
public class SelenePOJO {

    private final LocalDate date;
    private final List<SeleneDataPOJO> data;
    private final SeleneEventPOJO event;

    public SelenePOJO(LocalDate date, List<SeleneDataPOJO> data, SeleneEventPOJO event){
        this.date =date;
        this.data = data;
        this.event = event;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<SeleneDataPOJO> getData() {
        return data;
    }

    @JsonProperty("breaking news")
    public SeleneEventPOJO getEvent() {
        return event;
    }
}
