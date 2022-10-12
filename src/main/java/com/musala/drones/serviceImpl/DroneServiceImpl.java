package com.musala.drones.serviceImpl;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.enums.State;
import com.musala.drones.exception.ExcessLoadException;
import com.musala.drones.exception.NotFoundException;
import com.musala.drones.pojo.DronePojo;
import com.musala.drones.pojo.MedicationPojo;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.repository.MedicationRepository;
import com.musala.drones.service.DroneService;
import com.musala.drones.service.MedicationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Transactional
@Service
public class DroneServiceImpl implements DroneService {
    Logger log = LoggerFactory.getLogger(DroneService.class);
    private final int MIN_BATTERY_CAPACITY = 25;


    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    MedicationService medicationService;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public DronePojo registerDrone(DronePojo drone) {
        log.info("Registering drone");
        String serialNumber = UUID.randomUUID().toString();
        drone.setSerialNumber(serialNumber);
        drone.setWeightLimit(drone.getWeightLimit());
        drone.setRandomBatteryCapacity();
        drone.setState(State.IDLE);
        Drone droneEntity = modelMapper.map(drone, Drone.class);
        Drone registeredDrone = droneRepository.save(droneEntity);
        log.info("Drone registered successfully: {}", registeredDrone);
        return modelMapper.map(registeredDrone, DronePojo.class);
    }

    @Override
    public DronePojo loadDrone(String droneId, List<String> medicationIds) {
        log.info("Loading drone with medications");
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new NotFoundException("Drone not found"));

        boolean isLoadable = isDroneLoadable(drone.getBatteryCapacity());

        if (!isLoadable){
            log.info("Drone is not loadable at the moment - battery capacity is below 25%");
            throw new IllegalArgumentException("Drone is not loadable at the moment - battery capacity is below 25%");
        }
        List<Long> ids = medicationIds.stream().map(Long::parseLong).collect(Collectors.toList());
        List<Medication> medications = medicationRepository.findAllById(ids);
        boolean validWeightLoaded = isLoadedWeightValid(drone.getWeightLimit(), drone.getMedications(), medications);

        if (!validWeightLoaded){
            throw new ExcessLoadException("Drone cannot be loaded with an excess load");
        }
        drone.getMedications().addAll(medications);

        drone.setState(State.LOADED);
        Drone loadedDrone = droneRepository.save(drone);
        log.info("Drone loaded successfully: {}", loadedDrone);
        return modelMapper.map(loadedDrone, DronePojo.class);
    }

    @Override
    public Set<MedicationPojo> getDroneMedications(String serialNumber) {
        log.info("fetching drone medications");
        Drone drone = droneRepository.findById(serialNumber).orElseThrow(() -> new NotFoundException("Drone with serial number: "+ serialNumber + " does not exist"));
        Set<Medication> droneMedications = drone.getMedications();
        return modelMapper.map(droneMedications,  new TypeToken<Set<MedicationPojo>>() {}.getType());
    }


    @Override
    public List<DronePojo> dronesAvailable() {
        List<Drone> drones = droneRepository.findByStateAndBatteryCapacityGreaterThan(State.IDLE, MIN_BATTERY_CAPACITY);
        return modelMapper.map(drones, new TypeToken<List<DronePojo>>() {}.getType());
    }

    @Override
    public Integer checkDroneBatteryLevel(String serialNumber) {
        Drone drone = droneRepository.findById(serialNumber).orElseThrow(() -> new NotFoundException("Drone with serial number: "+ serialNumber + " does not exist"));
        return drone.getBatteryCapacity();
    }

    private boolean isDroneLoadable(int batteryCapacity){
        return batteryCapacity > MIN_BATTERY_CAPACITY;
    }

    private boolean isLoadedWeightValid(int limit, Set<Medication> loadedMedications, List<Medication> newMedications){
        int loadedWeight = 0;
        int incomingWeight = 0;

        for(Medication medication : loadedMedications){
            loadedWeight += medication.getWeight();
        }

        for(Medication medication : newMedications){
            incomingWeight += medication.getWeight();
        }

        if (loadedWeight + incomingWeight > limit) {
            return false;
        }

        return true;
    }

    public List<DronePojo> getAllDrones() {
        List<Drone> drones = droneRepository.findAll();
        return modelMapper.map(drones, new TypeToken<List<DronePojo>>() {}.getType());
    }
}
