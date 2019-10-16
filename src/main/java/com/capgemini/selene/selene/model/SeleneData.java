package com.capgemini.selene.selene.model;

public enum SeleneData {

	//EAU
	// Composés naturels
	CALCIUM("Calcium", Kind.WATER, Unit.MILLIGRAM_PER_LITER, "Ca2+", 5, 450),
	MAGNESIUM("Magnésium", Kind.WATER, Unit.MILLIGRAM_PER_LITER, "Mg2+", 0, 100),
	SODIUM("Sodium", Kind.WATER, Unit.MILLIGRAM_PER_LITER, "Na+", 0, 150),
	BICARBONATE("Bicarbonate", Kind.WATER, Unit.MILLIGRAM_PER_LITER, 100, 1000),
	SULFATES("Sulfates", Kind.WATER, Unit.MILLIGRAM_PER_LITER, 0, 130),
	CHLORURES("Chlorures", Kind.WATER, Unit.MILLIGRAM_PER_LITER, 0, 50),
	FLUORURES("Fluorures", Kind.WATER, Unit.MILLIGRAM_PER_LITER, 0, 30),
	SILICE("Silice", Kind.WATER, Unit.MILLIGRAM_PER_LITER, 0, 30),
	// Polluants
	NITRATES("Nitrates", Kind.WATER, Unit.MILLIGRAM_PER_LITER, 0, 4.5f),
	METAUX_LOURDS("Métaux lourds", Kind.WATER, Unit.MICROGRAM_PER_LITER, "Fe, Pb, Hg [mercure]",0, 0.1f),
	// Bactéries
	ESCHERICHIA_COLI_WATER("Escherichia coli", Kind.WATER, Unit.COLONY_FORMING_UNIT_PER_LITER, 0, 10),
	VIBRIO_CHOLEREA("Vibrio cholerea", Kind.WATER, Unit.COLONY_FORMING_UNIT_PER_LITER, 0, 0.1f),
	//AIR
	//Composés naturels
	DIAZOTE("Diazote", Kind.AIR, Unit.PERCENTAGE,"N2", 65, 72),
	DIOXYGENE("Diazote", Kind.AIR, Unit.PERCENTAGE,"O2", 17, 21),
	DIOXYDE_DE_CARBONE("Dioxyde de carbone", Kind.AIR, Unit.PERCENTAGE, "CO2", 0,0.8f),
	// Polluants
	DIOXYDE_AZOTE("Dioxyde d'azote",Kind.AIR, Unit.PERCENTAGE, "NO2", 0, 0.01f),
	DIOXYDE_DE_SOUFFRE("Dioxyde de souffre",Kind.AIR, Unit.PERCENTAGE, "SO2", 0, 0.03f),
	MONOXYDE_DE_CARBONE("Monoxyde de carbone",Kind.AIR, Unit.PERCENTAGE, "CO", 0, 0.09f),
	LEGIONELLA_PNEUMOPHILA("Legionelle pneumophila", Kind.AIR, Unit.PERCENTAGE, 0, 80),
	CONSOMMATION_ENERGIE("Consommation d'énergie", Kind.ENERGY, Unit.MEGAWATT_HOUR, 30, 180),
	// NOURRITURE
	// Polluants
	SALMONELLA_ENTERICA("Salmonella enterica", Kind.FOOD, Unit.COLONY_FORMING_UNIT_PER_KILOGRAM, 0, 9),
	ESCHERICHIA_COLI_FOOD("Escherichia coli", Kind.FOOD, Unit.COLONY_FORMING_UNIT_PER_KILOGRAM, 0, 50),
	LISTERIA_MONOCYTOGENES("Listeria monocytogenes", Kind.FOOD, Unit.COLONY_FORMING_UNIT_PER_KILOGRAM, 0, 250),
	//TEMPERATURE
	TEMPERATURE("Température", Kind.TEMPERATURE, Unit.CELSIUS_DEGREE, 18, 27),
	// PRESSION
	PRESSION("Pression atmosphérique", Kind.PRESSURE, Unit.ATMOSPHERE, 0.998f, 1.003f);


	private final String name;
	private final Kind kind;
	private final Unit unit;
	private String chemicalElement;
	private final float min;
	private final float max;

	SeleneData(String name, Kind kind, Unit unit, float min, float max) {
		this(name,kind, unit, "", min, max);
	}

	SeleneData(String name, Kind kind, Unit unit, String chemicalElement, float min, float max) {
		this.name = name;
		this.kind = kind;
		this.unit = unit;
		this.chemicalElement = chemicalElement;
		this.min = min;
		this.max = max;
	}

	public String getName(){
		return name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name);
		if(!("".equals(chemicalElement)))
			sb.append('(' + chemicalElement + ')');
		sb.append(", " + kind.getName());
		sb.append(", " + min + unit.getSymbol());
		sb.append(" < x < ");
		sb.append(max + unit.getSymbol());
		return sb.toString();
	}
}
