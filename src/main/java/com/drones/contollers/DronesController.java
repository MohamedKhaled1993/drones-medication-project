package com.drones.contollers;


import com.drones.dto.DronesDto;
import com.drones.dto.GeneralResponse;
import com.drones.services.DronesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/drone/")
public class DronesController {

   private final DronesService dronesService;

    public DronesController(DronesService dronesService) {
        this.dronesService = dronesService;
    }

    @GetMapping
    public ResponseEntity<GeneralResponse> getAllDrone() {
        return   ResponseEntity.ok(
                GeneralResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .data(Map.of("All drones ", dronesService.getAllDrones()))
                        .message("success")
                        .build()
        );
    }
    @GetMapping("/available")
    public ResponseEntity<GeneralResponse> checkAvailableDrones(){
        return   ResponseEntity.ok(
                GeneralResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .data(Map.of("All available drones", dronesService.getAvailableDrones()))
                        .message("success")
                        .build()
        );
    }
    @GetMapping("/checkBatteryBySerial/{serial}")
    public ResponseEntity<GeneralResponse> getCapacityForSerial(@PathVariable String serial) {

        return   ResponseEntity.ok(
                GeneralResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .data(Map.of("drone battery capacity", dronesService.checkDroneBatteryByDroneSerial(serial)))
                        .message("success")
                        .build()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<GeneralResponse> registerDrone(@RequestBody @Valid DronesDto dronesDto){

        return   ResponseEntity.ok(
                GeneralResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .data(Map.of("add drone", dronesService.registerDrone(dronesDto)))
                        .message("success")
                        .build()
        );

    }

    @PutMapping("/loadDrone/{droneSerial}")
    public ResponseEntity<GeneralResponse> loadDroneWithMedication(@PathVariable(value = "droneSerial") String droneSerial,@RequestBody List<String> medicationsCodesList){
        return   ResponseEntity.ok(
                GeneralResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .data(Map.of("load a drone with medication", dronesService.loading_drone_with_medication(droneSerial,medicationsCodesList)))
                        .message("success")
                        .build()
        );
    }

    @GetMapping("medicationsByDrone/{droneSerial}")
    public ResponseEntity<GeneralResponse> checkMedicationsByDrone(@PathVariable(value = "droneSerial") String serialNumber){
        return   ResponseEntity.ok(
                GeneralResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .data(Map.of("medication in drone", dronesService.checkLoadedMedicationsByDrone(serialNumber)))
                        .message("success")
                        .build()
        );

    }

}
