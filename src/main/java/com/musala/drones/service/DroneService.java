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

    DronePojo registerDrone(DronePojo drone);

    DronePojo loadDrone(String droneId, List<String> medications);

    Set<MedicationPojo> getDroneMedications(String serialNumber);

    List<DronePojo> dronesAvailable();

    Integer checkDroneBatteryLevel(String droneId);

    List<DronePojo> getAllDrones();
}
