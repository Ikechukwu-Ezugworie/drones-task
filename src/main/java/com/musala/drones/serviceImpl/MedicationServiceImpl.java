package com.musala.drones.serviceImpl;

import com.musala.drones.entities.Medication;
import com.musala.drones.pojo.MedicationPojo;
import com.musala.drones.repository.MedicationRepository;
import com.musala.drones.service.DroneService;
import com.musala.drones.service.MedicationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {
    Logger log = LoggerFactory.getLogger(MedicationServiceImpl.class);


    @Autowired
    MedicationRepository medicationRepository;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public MedicationPojo createMedication(MedicationPojo medication) {
        log.info("Creating medication");
        Medication medicationEntity = modelMapper.map(medication, Medication.class);
        Medication createdMedication = medicationRepository.save(medicationEntity);
        log.info("Medication created successfully: {}", createdMedication);
        return modelMapper.map(createdMedication, MedicationPojo.class);
    }

    @Override
    public List<MedicationPojo> createMedications(@RequestBody @Valid List<MedicationPojo> medications) {
        List<Medication> medicationEntity = modelMapper.map(medications, new TypeToken<List<Medication>>() {}.getType());
        List<Medication> createdMedications = medicationRepository.saveAll(medicationEntity);
        return modelMapper.map(createdMedications, new TypeToken<List<MedicationPojo>>() {}.getType());
    }

    @Override
        public List<Medication> getMedicationByIds(List<Long> ids) {
        ids.forEach(id -> {
            if (!medicationRepository.existsById(id)) {
                throw new IllegalArgumentException("Medication with id " + id + " does not exist");
            }
        });
        return medicationRepository.findAllById(ids);
    }

    @Override
    public List<Medication> randomMedications(int limit) {
        return null;
    }
}
