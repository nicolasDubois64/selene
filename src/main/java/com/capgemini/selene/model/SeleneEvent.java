package com.capgemini.selene.model;

public class SeleneEvent {
	private String title;
	private String description;
	private String criticality;
	private double probability;
	
	public SeleneEvent() {
		super();
	}

	public SeleneEvent(String title, String description, String criticality, int probability) {
		this.title = title;
		this.description = description;
		this.criticality = criticality;
		this.probability = probability;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCriticality() {
		return criticality;
	}

	public void setCriticality(String criticality) {
		this.criticality = criticality;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}
}
