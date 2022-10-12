package com.musala.drones.pojo;

import com.musala.drones.enums.State;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DronePojo {
    private String serialNumber;
    @NotNull(message = "model must be of type lightweight, middleweight, cruiserweight or heavyweight")
    @Pattern(regexp="^(lightweight|middleweight|cruiserweight|heavyweight)$",
            message="model must be of type lightweight, middleweight, cruiserweight or heavyweight")
    private String model;
    @Max(value = 500)
    private int weightLimit;
    private int batteryCapacity;
    private State state;
    private Set<MedicationPojo> medications;

    public DronePojo() {
    }

    public DronePojo(String model) {
        this.model = model;
    }

    public DronePojo(String model, int batteryCapacity, State state) {
        this.model = model;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void setRandomBatteryCapacity() {
        int min = 20;
        int max = 100;
        Random random = new Random();
        int randomInt = (int) (random.nextFloat() * (max - min) + min);
        this.batteryCapacity = randomInt;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<MedicationPojo> getMedications() {
        return medications;
    }

    public void setMedications(Set<MedicationPojo> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        return "DronePojo{" +
                "serialNumber='" + serialNumber + '\'' +
                ", model=" + model +
                ", weightLimit=" + weightLimit +
                ", batteryCapacity=" + batteryCapacity +
                ", state=" + state +
                ", medications=" + medications +
                '}';
    }
}
