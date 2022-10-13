package com.musala.drones.utils;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.enums.Model;
import com.musala.drones.enums.State;
import com.musala.drones.enums.WeightLimit;
import com.musala.drones.pojo.DronePojo;

import java.util.*;

public class Utility {

    /**
     * Define new medications
     * @return a collection of medications
     */
    public static Set<Medication> defineMedications(){
        Medication medication1 = new Medication(1L,"Paracetamol", 20,"PARA_001", "med_1_image");
        Medication medication2 = new Medication(2L,"med_2", 20, "MED_2_CODE", "med_2_image");
        Medication medication3 = new Medication(3L,"med_3", 30, "MED_3_CODE", "med_3_image");
        Set<Medication> medications = new HashSet<>();
        medications.add(medication1);
        medications.add(medication2);
        medications.add(medication3);
        return medications;
    }

    /**
     * Define a drone
     * @return a drone object
     */
    public static Drone defineADrone() {
        String serialNumber = UUID.randomUUID().toString();
        Drone drone = new Drone(serialNumber, Model.CRUISER_WEIGHT.getModel(), WeightLimit.HEAVY_WEIGHT.getWeightLimit(), 60, State.IDLE, new HashSet<>());
       return drone;
    }
}
