package com.capgemini.selene.model;

public class SeleneEventPOJO {

    private final String title;
    private final String description;
    private final String criticality;

    public SeleneEventPOJO(SeleneEvent event){
        title = event.getTitle();
        description = event.getDescription();
        criticality = event.getCriticality();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

	public String getCriticality() {
		return criticality;
	}
    
    
}
