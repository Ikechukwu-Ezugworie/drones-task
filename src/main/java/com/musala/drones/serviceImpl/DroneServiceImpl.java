package com.musala.drones.serviceImpl;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    DroneRepository droneRepository;
    @Override
    public Drone registerDrone(Drone drone) {
        return null;
    }

    @Override
    public Drone loadDrone(String droneId, List<Medication> medications) {
        return null;
    }

    @Override
    public List<Medication> getDroneMedications(String droneId) {
        return null;
    }

    @Override
    public List<Drone> dronesAvailable(String droneId) {
        return null;
    }

    @Override
    public Integer checkDroneBatteryLevel(String droneId) {
        return null;
    }
}
