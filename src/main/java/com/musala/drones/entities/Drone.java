package com.musala.drones.entities;

import com.musala.drones.enums.Model;
import com.musala.drones.enums.State;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Drone {
    @Id
    private String serialNumber;
    private Model model;
    private int weight;
    private int batteryCapacity;
    private State state;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
