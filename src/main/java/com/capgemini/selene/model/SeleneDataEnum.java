package com.capgemini.selene.model;

public enum SeleneDataEnum {

    //EAU
    // Composés naturels
    CALCIUM("Calcium", Kind.WATER, false, Unit.MILLIGRAM_PER_LITER, "Ca2+", 5, 450),
    MAGNESIUM("Magnésium", Kind.WATER, false, Unit.MILLIGRAM_PER_LITER, "Mg2+", 0, 100),
    SODIUM("Sodium", Kind.WATER, false, Unit.MILLIGRAM_PER_LITER, "Na+", 0, 150),
    BICARBONATE("Bicarbonate", Kind.WATER, false, Unit.MILLIGRAM_PER_LITER, 100, 1000),
    SULFATES("Sulfates", Kind.WATER, false, Unit.MILLIGRAM_PER_LITER, 0, 130),
    CHLORURES("Chlorures", Kind.WATER, false, Unit.MILLIGRAM_PER_LITER, 0, 50),
    FLUORURES("Fluorures", Kind.WATER, false, Unit.MILLIGRAM_PER_LITER, 0, 30),
    SILICE("Silice", Kind.WATER, false, Unit.MILLIGRAM_PER_LITER, 0, 30),
    // Polluants
    NITRATES("Nitrates", Kind.WATER, true, Unit.MILLIGRAM_PER_LITER, 0, 4.5f),
    METAUX_LOURDS("Métaux lourds", Kind.WATER, true, Unit.MICROGRAM_PER_LITER, "Fe, Pb, Hg [mercure]", 0, 0.1f),
    // Bactéries
    ESCHERICHIA_COLI_WATER("Escherichia coli", Kind.WATER, true, Unit.COLONY_FORMING_UNIT_PER_LITER, 0, 10),
    VIBRIO_CHOLEREA("Vibrio cholerea", Kind.WATER, true, Unit.COLONY_FORMING_UNIT_PER_LITER, 0, 0.1f),
    //AIR
    //Composés naturels
    DIAZOTE("Diazote", Kind.AIR, false, Unit.PERCENTAGE, "N2", 65, 72),
    DIOXYGENE("Dioxygène", Kind.AIR, false, Unit.PERCENTAGE, "O2", 17, 21),
    DIOXYDE_DE_CARBONE("Dioxyde de carbone", Kind.AIR, false, Unit.PERCENTAGE, "CO2", 0, 0.8f),
    // Polluants
    DIOXYDE_AZOTE("Dioxyde d'azote", Kind.AIR, true, Unit.PERCENTAGE, "NO2", 0, 0.01f),
    DIOXYDE_DE_SOUFFRE("Dioxyde de souffre", Kind.AIR, true, Unit.PERCENTAGE, "SO2", 0, 0.03f),
    MONOXYDE_DE_CARBONE("Monoxyde de carbone", Kind.AIR, true, Unit.PERCENTAGE, "CO", 0, 0.09f),
    LEGIONELLA_PNEUMOPHILA("Legionelle pneumophila", Kind.AIR, true, Unit.PERCENTAGE, 0, 80),
    CONSOMMATION_ENERGIE("Consommation d'énergie", Kind.ENERGY, true, Unit.MEGAWATT_HOUR, 30, 180),
    // NOURRITURE
    // Polluants
    SALMONELLA_ENTERICA("Salmonella enterica", Kind.FOOD, true, Unit.COLONY_FORMING_UNIT_PER_KILOGRAM, 0, 9),
    ESCHERICHIA_COLI_FOOD("Escherichia coli", Kind.FOOD, true, Unit.COLONY_FORMING_UNIT_PER_KILOGRAM, 0, 50),
    LISTERIA_MONOCYTOGENES("Listeria monocytogenes", Kind.FOOD, true, Unit.COLONY_FORMING_UNIT_PER_KILOGRAM, 0, 250),
    //TEMPERATURE
    TEMPERATURE("Température", Kind.TEMPERATURE, false, Unit.CELSIUS_DEGREE, 18, 27),
    // PRESSION
    PRESSION("Pression atmosphérique", Kind.PRESSURE, false, Unit.ATMOSPHERE, 0.998f, 1.003f);


    private final String name;
    private final Kind kind;
    private final boolean isPolluant;
    private final Unit unit;
    private String chemicalElement;
    private final float min;
    private final float max;

    SeleneDataEnum(String name, Kind kind, boolean isPolluant, Unit unit, float min, float max) {
        this(name, kind, isPolluant, unit, "", min, max);
    }

    SeleneDataEnum(String name, Kind kind, boolean isPolluant, Unit unit, String chemicalElement, float min, float max) {
        this.name = name;
        this.kind = kind;
        this.isPolluant = isPolluant;
        this.unit = unit;
        this.chemicalElement = chemicalElement;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append(System.lineSeparator());
        sb.append("\"name\" : \"").append(name).append("\",").append(System.lineSeparator());
        sb.append("\"kind\" : \"").append(kind).append("\",").append(System.lineSeparator());
        sb.append("\"isPolluant\" : \"").append(isPolluant).append("\",").append(System.lineSeparator());
        sb.append("\"unit\" : \"").append(unit).append("\",").append(System.lineSeparator());
        sb.append("\"chemicalElement\" : \"").append(chemicalElement).append("\",").append(System.lineSeparator());
        sb.append("\"min\" : \"").append(min).append("\",").append(System.lineSeparator());
        sb.append("\"max\" : \"").append(max).append("\"").append(System.lineSeparator());
        sb.append("},");
        return sb.toString();
    }

    public Kind getKind() {
        return kind;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getChemicalElement() {
        return chemicalElement;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public boolean isPolluant() {
        return isPolluant;
    }
}
