package com.musala.drones.service;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DroneService {

    Drone registerDrone(Drone drone);

    Drone loadDrone(String droneId, List<Medication> medications);

    List<Medication> getDroneMedications(String droneId);

    List<Drone> dronesAvailable(String droneId);

    Integer checkDroneBatteryLevel(String droneId);
}
