package com.musala.drones.service;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.pojo.DronePojo;
import com.musala.drones.pojo.MedicationPojo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DroneService {

    DronePojo registerDrone(Drone drone);

    DronePojo loadDrone(String droneId, List<Medication> medications);

    List<MedicationPojo> getDroneMedications(String droneId);

    List<DronePojo> dronesAvailable(String droneId);

    Integer checkDroneBatteryLevel(String droneId);
}
