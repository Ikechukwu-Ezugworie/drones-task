package com.musala.drones.utils;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.enums.Model;
import com.musala.drones.enums.State;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static List<Medication> defineMedications(){
        Medication medication1 = new Medication(1L,"med_1", 10, "MED_1_CODE", "med_1_image");
        Medication medication2 = new Medication(1L,"med_2", 20, "MED_2_CODE", "med_2_image");
        Medication medication3 = new Medication(1L,"med_3", 30, "MED_3_CODE", "med_3_image");
        List<Medication> medications = new ArrayList<>();
        medications.add(medication1);
        medications.add(medication2);
        medications.add(medication3);
        return medications;
    }

    public static Drone defineADrone() {
       Drone drone = new Drone("123", Model.MIDDLE_WEIGHT, 200, 60, State.IDLE);
       return drone;
    }
}
