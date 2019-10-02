package com.capgemini.selene.selene.model;

public class AirData {
	float dioxygene;
	float diazote;
	float dioxydeCarbonne;
	
	public AirData() {}

	public AirData(float dioxygene, float diazote, float dioxydeCarbonne) {
		this.dioxygene = dioxygene;
		this.diazote = diazote;
		this.dioxydeCarbonne = dioxydeCarbonne;
	}

	public float getDioxygene() {
		return dioxygene;
	}

	public void setDioxygene(float dioxygene) {
		this.dioxygene = dioxygene;
	}

	public float getDiazote() {
		return diazote;
	}

	public void setDiazote(float diazote) {
		this.diazote = diazote;
	}

	public float getDioxydeCarbonne() {
		return dioxydeCarbonne;
	}

	public void setDioxydeCarbonne(float dioxydeCarbonne) {
		this.dioxydeCarbonne = dioxydeCarbonne;
	}
}
