package com.musala.drones.serviceImpl;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.pojo.DronePojo;
import com.musala.drones.pojo.MedicationPojo;
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
    public DronePojo registerDrone(Drone drone) {
        return null;
    }

    @Override
    public DronePojo loadDrone(String droneId, List<Medication> medications) {
        return null;
    }

    @Override
    public List<MedicationPojo> getDroneMedications(String droneId) {
        return null;
    }

    @Override
    public List<DronePojo> dronesAvailable(String droneId) {
        return null;
    }

    @Override
    public Integer checkDroneBatteryLevel(String droneId) {
        return null;
    }
}
