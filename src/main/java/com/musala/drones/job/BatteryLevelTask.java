package com.musala.drones.job;

import com.musala.drones.pojo.DronePojo;
import com.musala.drones.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//@Component
public class BatteryLevelTask {
    Logger log = LoggerFactory.getLogger(BatteryLevelTask.class);

//    @Autowired
    private DroneService droneService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    @Scheduled(cron = "05 * * * * *")
    public void execute() {
        log.info("*********************************");
        log.info("Checking all drones battery level");
        List<DronePojo> drones = droneService.getAllDrones();

        if (!drones.isEmpty()) {
            for (DronePojo drone : drones) {
                log.info("Drone with serial number {}, has {}% battery capacity - Time: {}",
                        drone.getSerialNumber(),
                        drone.getBatteryCapacity(),
                        formatter.format(LocalDateTime.now())
                );
            }
        } else {
            log.info("No Drone found!");
        }
        log.info("End of battery check");
        log.info("*********************************");

    }
}
