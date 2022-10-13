package com.musala.drones.service;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.enums.Model;
import com.musala.drones.enums.State;
import com.musala.drones.pojo.DronePojo;
import com.musala.drones.pojo.MedicationPojo;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.repository.MedicationRepository;
import com.musala.drones.serviceImpl.DroneServiceImpl;
import com.musala.drones.utils.Utility;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DroneServiceTest {

    @InjectMocks
    DroneServiceImpl droneServiceImpl;

    @Mock
    DroneRepository droneRepository;

    @Mock
    MedicationRepository medicationRepository;

    @Mock
    Drone drone = new Drone();

    @Mock
    DronePojo dronePojo = new DronePojo();

    @Mock
    Set<MedicationPojo> medicationPojos = new HashSet<>();

    @Mock
    Set<Medication> medications = new HashSet<>();

    @Mock
    ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setUp() {
        droneRepository.deleteAll();
        drone = Utility.defineADrone();
        dronePojo = modelMapper.map(drone, DronePojo.class);
        medications = Utility.defineMedications();
    }

    @Test
    void registerDrone() {
        dronePojo = new DronePojo(Model.CRUISER_WEIGHT.getModel());
        when(droneRepository.save(drone)).thenReturn(drone);
        when(modelMapper.map(dronePojo, Drone.class)).thenReturn(drone);
        when(modelMapper.map(drone, DronePojo.class)).thenReturn(dronePojo);
        dronePojo = droneServiceImpl.registerDrone(dronePojo);
        assertNotNull(dronePojo);
    }

    @Test
    void loadDrone() {
        medicationRepository.saveAll(medications);
        this.registerDrone();

        List<Long> medicationIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        List<String> medicationIdsInString = new ArrayList<>(Arrays.asList("1", "2", "3"));

        when(droneRepository.findById(drone.getSerialNumber())).thenReturn(Optional.ofNullable(drone));
        when(medicationRepository.findAllById(medicationIds)).thenReturn(new ArrayList<>(medications));
        when(droneRepository.save(drone)).thenReturn(drone);
        when(modelMapper.map(drone, DronePojo.class)).thenReturn(dronePojo);

        dronePojo = droneServiceImpl.loadDrone(drone.getSerialNumber(), medicationIdsInString);
        drone = modelMapper.map(dronePojo, Drone.class);
        assertNotNull(drone.getMedications());
        assertFalse(drone.getMedications().isEmpty());
    }

    @Test
    void getDroneMedications() {
        this.registerDrone();
        this.loadDrone();
        when(droneRepository.findById(drone.getSerialNumber())).thenReturn(Optional.ofNullable(drone));
        when(modelMapper.map(medications,  new TypeToken<Set<MedicationPojo>>() {}.getType())).thenReturn(medications);
        medicationPojos = droneServiceImpl.getDroneMedications(drone.getSerialNumber());
        medicationPojos = modelMapper.map(medications,  new TypeToken<Set<MedicationPojo>>() {}.getType());
        assertNotNull(medicationPojos);
        assertFalse(medicationPojos.isEmpty());
    }

    @Test
    void dronesAvailable() {
        this.registerDrone();
        when(droneRepository.findByStateAndBatteryCapacityGreaterThan(State.IDLE, 25)).thenReturn(List.of(drone));
        when(modelMapper.map(List.of(drone), new TypeToken<List<DronePojo>>() {}.getType())).thenReturn(List.of(dronePojo));
        List<DronePojo> dronesAvailable = droneServiceImpl.dronesAvailable();
        assertNotNull(dronesAvailable);
        assertFalse(dronesAvailable.isEmpty());
    }

    @Test
    void checkDroneBatteryLevel() {
        this.registerDrone();
        when(droneRepository.findById(drone.getSerialNumber())).thenReturn(Optional.ofNullable(drone));
        Integer batteryLevel = droneServiceImpl.checkDroneBatteryLevel(drone.getSerialNumber());
        assertNotNull(batteryLevel);
    }

}