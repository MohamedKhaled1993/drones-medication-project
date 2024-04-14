package com.drones.services;

import com.drones.dto.MedicationDto;
import com.drones.entities.Medications;
import com.drones.repositories.MedicationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface MedicationsService {

    public MedicationDto registerMedication(MedicationDto medicationDto);

    public MedicationDto saveMedication(MedicationDto medications);
}
