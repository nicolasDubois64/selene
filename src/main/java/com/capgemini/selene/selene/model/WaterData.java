package com.capgemini.selene.selene.model;

public class WaterData {
	float calcium;
	float magnesium;
	float sodium;
	float potasium;
	float bicarbonates;
	float sulfates;
	float clhorues;
	float nitrates;
	float fluorure;
	
	public WaterData() {
		super();
	}

	public WaterData(float calcium, float magnesium, float sodium, float potasium, float bicarbonates, float sulfates,
			float clhorues, float nitrates, float fluorure) {
		this.calcium = calcium;
		this.magnesium = magnesium;
		this.sodium = sodium;
		this.potasium = potasium;
		this.bicarbonates = bicarbonates;
		this.sulfates = sulfates;
		this.clhorues = clhorues;
		this.nitrates = nitrates;
		this.fluorure = fluorure;
	}

	public float getCalcium() {
		return calcium;
	}

	public void setCalcium(float calcium) {
		this.calcium = calcium;
	}

	public float getMagnesium() {
		return magnesium;
	}

	public void setMagnesium(float magnesium) {
		this.magnesium = magnesium;
	}

	public float getSodium() {
		return sodium;
	}

	public void setSodium(float sodium) {
		this.sodium = sodium;
	}

	public float getPotasium() {
		return potasium;
	}

	public void setPotasium(float potasium) {
		this.potasium = potasium;
	}

	public float getBicarbonates() {
		return bicarbonates;
	}

	public void setBicarbonates(float bicarbonates) {
		this.bicarbonates = bicarbonates;
	}

	public float getSulfates() {
		return sulfates;
	}

	public void setSulfates(float sulfates) {
		this.sulfates = sulfates;
	}

	public float getClhorues() {
		return clhorues;
	}

	public void setClhorues(float clhorues) {
		this.clhorues = clhorues;
	}

	public float getNitrates() {
		return nitrates;
	}

	public void setNitrates(float nitrates) {
		this.nitrates = nitrates;
	}

	public float getFluorure() {
		return fluorure;
	}

	public void setFluorure(float fluorure) {
		this.fluorure = fluorure;
	}
}
