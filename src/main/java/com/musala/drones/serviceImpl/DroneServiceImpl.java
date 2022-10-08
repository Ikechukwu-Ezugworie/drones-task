package com.musala.drones.serviceImpl;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.enums.State;
import com.musala.drones.pojo.DronePojo;
import com.musala.drones.pojo.MedicationPojo;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.service.DroneService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Transactional
@Service
public class DroneServiceImpl implements DroneService {
    Logger log = LoggerFactory.getLogger(DroneService.class);


    @Autowired
    DroneRepository droneRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public DronePojo registerDrone(DronePojo drone) {
        log.info("Registering drone");
        String serialNumber = UUID.randomUUID().toString();
        drone.setSerialNumber(serialNumber);
        drone.setWeightLimit(drone.getWeightLimit());
        drone.setBatteryCapacity(100);
        drone.setState(State.IDLE);
        Drone droneEntity = modelMapper.map(drone, Drone.class);
        Drone registeredDrone = droneRepository.save(droneEntity);
        log.info("Drone registered successfully: {}", registeredDrone);
        return modelMapper.map(registeredDrone, DronePojo.class);
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
