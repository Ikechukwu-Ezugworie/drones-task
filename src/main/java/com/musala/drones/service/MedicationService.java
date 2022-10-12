package com.musala.drones.service;

import com.musala.drones.entities.Medication;
import com.musala.drones.pojo.MedicationPojo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicationService {

    MedicationPojo createMedication(MedicationPojo medication);

    List<MedicationPojo> createMedications(List<MedicationPojo> medications);

    List<Medication> getMedicationByIds(List<Long> ids);

    List<Medication> randomMedications(int limit);
}
