package com.capgemini.selene.model;

public class SeleneEventPOJO {

    private final String title;
    private final String description;

    public SeleneEventPOJO(SeleneEvent event){
        title = event.getTitle();
        description = event.getDescription();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
