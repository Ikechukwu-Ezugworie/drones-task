package com.musala.drones.enums;

public enum WeightLimit {
    LIGHT_WEIGHT(125),
    MIDDLE_WEIGHT(250),
    CRUISER_WEIGHT(375),
    HEAVY_WEIGHT(500);



    private int weightLimit;

    WeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getWeightLimit() {
        return weightLimit;
    }
}
