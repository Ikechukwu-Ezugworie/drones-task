package com.musala.drones.service;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.pojo.DronePojo;
import com.musala.drones.pojo.MedicationPojo;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.utils.Utility;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class DroneServiceTest {

    @Autowired
    DroneService droneService;

    @Autowired
    DroneRepository droneRepository;

    DronePojo drone = null;
    List<Medication> medications = null;

    @BeforeEach
    void setUp() {
        droneRepository.deleteAll();
        drone = Utility.defineADrone();
        medications = Utility.defineMedications();
    }

    @Test
    void registerDrone() {
        DronePojo registeredDrone = droneService.registerDrone(drone);
        assertNotNull(registeredDrone);
    }

    @Test
    void loadDrone() {
        DronePojo loadedDrone = droneService.loadDrone(drone.getSerialNumber(), medications);
        assertNotNull(loadedDrone);
        assertFalse(loadedDrone.getMedications().isEmpty());
    }

    @Test
    void getDroneMedications() {
        List<MedicationPojo> medications = droneService.getDroneMedications(drone.getSerialNumber());
        assertNotNull(medications);
        assertFalse(medications.isEmpty());
    }

    @Test
    void dronesAvailable() {
        List<DronePojo> dronesAvailable = droneService.dronesAvailable(drone.getSerialNumber());
        assertNotNull(dronesAvailable);
        assertFalse(dronesAvailable.isEmpty());
    }

    @Test
    void checkDroneBatteryLevel() {
        Integer batteryLevel = droneService.checkDroneBatteryLevel(drone.getSerialNumber());
        assertNotNull(batteryLevel);
    }

}