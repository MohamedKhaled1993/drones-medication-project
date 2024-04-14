package com.drones.services.implementation;

import com.drones.dto.MedicationDto;
import com.drones.entities.Medications;
import com.drones.exceptions.ExistedResourceException;
import com.drones.repositories.MedicationsRepository;
import com.drones.services.MedicationsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MedicationsServiceImpl implements MedicationsService {

    private final MedicationsRepository medicationsRepository;
    private final ModelMapper modelMapper;

    public MedicationsServiceImpl(MedicationsRepository medicationsRepository, ModelMapper modelMapper) {
        this.medicationsRepository = medicationsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedicationDto registerMedication(MedicationDto medicationDto) {
       Medications med= medicationsRepository.save(mapToEntity(medicationDto));

        return mapToDto(med);
    }

    @Override
    public MedicationDto saveMedication(MedicationDto medicationDto) {

        log.info("starting register new medication ");

        if(!medicationsRepository.findById(medicationDto.getCode()).isEmpty()){
            throw new ExistedResourceException("Medication code "+medicationDto.getCode()+" already exists");

        }
        Medications medications=medicationsRepository.save(mapToEntity(medicationDto));
        return mapToDto(medications);
    }


    private MedicationDto mapToDto(Medications medications)
    {

        return modelMapper.map(medications,MedicationDto.class);
    }
    private Medications mapToEntity(MedicationDto medicationDto)
    {

        return modelMapper.map(medicationDto,Medications.class);
    }
}
