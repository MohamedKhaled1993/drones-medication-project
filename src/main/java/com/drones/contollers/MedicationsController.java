package com.drones.contollers;

import com.drones.dto.MedicationDto;
import com.drones.services.MedicationsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/medication/")
public class MedicationsController {

   private final MedicationsService medicationsService;

    public MedicationsController(MedicationsService medicationsService) {
        this.medicationsService = medicationsService;
    }



    @PostMapping
    public ResponseEntity<MedicationDto> addMedication(@RequestBody @Valid MedicationDto medicationDto){
        return ResponseEntity.ok(medicationsService.saveMedication(medicationDto));
    }
}
