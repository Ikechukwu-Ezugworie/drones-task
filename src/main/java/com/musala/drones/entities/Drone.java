package com.musala.drones.entities;

import com.musala.drones.enums.Model;
import com.musala.drones.enums.State;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Drone {
    @Id
    @Column(length = 100)
    private String serialNumber;
    @NotNull
    private String model;
    @Max(value = 500, message = "weight limit cannot be more than 500")
    @NotNull
    private int weightLimit;
    @NotNull
    private int batteryCapacity;
    @NotNull
    private State state;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "drone_medications",
            joinColumns = @JoinColumn(name = "drone_serial_number"),
            inverseJoinColumns = @JoinColumn(name = "medication_code")
    )
    private Set<Medication> medications;


    public Drone() {
    }

    public Drone(String serialNumber, String model, int weightLimit, int batteryCapacity, State state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public Drone(String serialNumber, String model, int weightLimit, int batteryCapacity, State state, Set<Medication> medications) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.medications = medications;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "serialNumber='" + serialNumber + '\'' +
                ", model=" + model +
                ", weightLimit=" + weightLimit +
                ", batteryCapacity=" + batteryCapacity +
                ", state=" + state +
                ", medications=" + medications +
                '}';
    }
}
