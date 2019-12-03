package com.capgemini.selene.model;

public class SeleneEvent {
	private String title;
	private String description;
	private String criticality;
	private double probability;
	private int duration;
	
	public SeleneEvent() {
		super();
	}

	public SeleneEvent(String title, String description, String criticality, int probability, int duration) {
		this.title = title;
		this.description = description;
		this.criticality = criticality;
		this.probability = probability;
		this.duration = duration;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
