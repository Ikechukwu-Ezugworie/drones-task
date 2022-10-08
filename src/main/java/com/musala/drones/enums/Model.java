package com.musala.drones.enums;

import com.fasterxml.jackson.annotation.JsonValue;
public enum Model {

    LIGHT_WEIGHT("Lightweight", 125),
    MIDDLE_WEIGHT("Middleweight", 250),
    CRUISER_WEIGHT("Cruiserweight", 375),
    HEAVY_WEIGHT("Heavyweight", 500);

    private String model;

    private int weightLimit;

    Model(String model, int weightLimit) {
        this.model = model;
        this.weightLimit = weightLimit;
    }

    @JsonValue
    public String getModel() {
        return this.model.toLowerCase();
    }

    public static int getWeightLimit(String modelInput) {
        for (Model val : Model.values()) {
            if (String.valueOf(val.model).equalsIgnoreCase(modelInput)) {
                return val.weightLimit;
            }
        }
        return LIGHT_WEIGHT.weightLimit;
    }
}
