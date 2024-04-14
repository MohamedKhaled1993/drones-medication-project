package com.drones.contollers;

import com.drones.dto.GeneralResponse;
import com.drones.dto.MedicationDto;
import com.drones.services.MedicationsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/medication/")
public class MedicationsController {

   private final MedicationsService medicationsService;

    public MedicationsController(MedicationsService medicationsService) {
        this.medicationsService = medicationsService;
    }



    @PostMapping
    public ResponseEntity<GeneralResponse> addMedication(@RequestBody @Valid MedicationDto medicationDto){
        return   ResponseEntity.ok(
                GeneralResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .data(Map.of("add medication", medicationsService.saveMedication(medicationDto)))
                        .message("success")
                        .build()
        );
    }
}
