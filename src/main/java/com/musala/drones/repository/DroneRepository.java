package com.musala.drones.repository;

import com.musala.drones.entities.Drone;
import com.musala.drones.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {
    List<Drone> findByStateAndBatteryCapacityGreaterThan(State state, int limit);
}
