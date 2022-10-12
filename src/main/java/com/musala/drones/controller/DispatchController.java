package com.musala.drones.controller;

import com.musala.drones.entities.Medication;
import com.musala.drones.pojo.DronePojo;
import com.musala.drones.pojo.MedicationPojo;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/drones")
public class DispatchController {

    @Autowired
    private DroneService droneService;

    @Autowired
    private DroneRepository droneRepository;


    @PostMapping
    public ResponseEntity<DronePojo> registerDrone(@RequestBody @Valid DronePojo dronePojo) {
        return ResponseEntity.ok(droneService.registerDrone(dronePojo));
    }

    @PutMapping("{serialNumber}/medications")
    public ResponseEntity<DronePojo> loadDrone(@PathVariable String serialNumber, @RequestBody List<String> medicationIds) {
        return ResponseEntity.ok(droneService.loadDrone(serialNumber, medicationIds));
    }

    @GetMapping("{serialNumber}/medications")
    public ResponseEntity<Set<MedicationPojo>> checkDroneMedications(@PathVariable String serialNumber){
        return ResponseEntity.ok(droneService.getDroneMedications(serialNumber));
    }
}
