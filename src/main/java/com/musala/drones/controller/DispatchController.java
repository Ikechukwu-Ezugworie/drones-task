package com.musala.drones.controller;

import com.musala.drones.pojo.DronePojo;
import com.musala.drones.pojo.MedicationPojo;
import com.musala.drones.service.DroneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/drones")
public class DispatchController {

    @Autowired
    private DroneService droneService;


    @Operation(summary = "register a new drone")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "500", description = "internal error - critical!")})
    @PostMapping
    public ResponseEntity<DronePojo> registerDrone(@RequestBody @Valid DronePojo dronePojo) {
        return ResponseEntity.ok(droneService.registerDrone(dronePojo));
    }

    @Operation(summary = "load a drone with medication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "loaded drone",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid serialNumber supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "drone not found",
                    content = @Content)})
    @PutMapping("{serialNumber}/medications")
    public ResponseEntity<DronePojo> loadDrone(@PathVariable @Valid String serialNumber, @RequestBody List<String> medicationIds) {
        return ResponseEntity.ok(droneService.loadDrone(serialNumber, medicationIds));
    }

    @Operation(summary = "check medications loaded to a drone")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "500", description = "internal error - critical!")})
    @GetMapping("{serialNumber}/medications")
    public ResponseEntity<Set<MedicationPojo>> checkDroneMedications(@PathVariable @Valid String serialNumber) {
        return ResponseEntity.ok(droneService.getDroneMedications(serialNumber));
    }

    @Operation(summary = "get drones available for loading")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "500", description = "internal error - critical!")})
    @GetMapping("available")
    public ResponseEntity<List<DronePojo>> getAvailableDrones() {
        return ResponseEntity.ok(droneService.dronesAvailable());
    }

    @Operation(summary = "get battery level of a drone")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "500", description = "internal error - critical!")})
    @GetMapping("{serialNumber}/battery")
    public ResponseEntity<Integer> getBatteryLevel(@PathVariable @Valid String serialNumber) {
        return ResponseEntity.ok(droneService.checkDroneBatteryLevel(serialNumber));
    }

}
