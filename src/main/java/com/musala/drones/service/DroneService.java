package com.musala.drones.service;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.pojo.DronePojo;
import com.musala.drones.pojo.MedicationPojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface DroneService {

    /**
     * Register a drone
     * @param drone
     * @return DronePojo object
     */
    DronePojo registerDrone(DronePojo drone);

    /**
     * load a drone with medications
     * @param droneId
     * @param medications
     * @return DronePojo object
     */
    DronePojo loadDrone(String droneId, List<String> medications);

    /**
     * get drone medications
     * @param serialNumber
     * @return a collection of medications (empty collection if there are none)
     */
    Set<MedicationPojo> getDroneMedications(String serialNumber);

    /**
     * get the drones available for loading
     * @return
     */
    List<DronePojo> dronesAvailable();

    /**
     * get battery level of a drone
     * @param droneId
     * @return the battery level in integer
     */
    Integer checkDroneBatteryLevel(String droneId);

    /**
     * get all drones
     * @return collection of drones
     */
    List<DronePojo> getAllDrones();
}
