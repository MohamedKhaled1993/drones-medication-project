package com.drones.contollers;


import com.drones.dto.DronesDto;
import com.drones.dto.GeneralResponse;
import com.drones.entities.Drones;
import com.drones.entities.Medications;
import com.drones.services.DronesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PostMapping
    public ResponseEntity<DronesDto> registerDrone(@RequestBody @Valid DronesDto dronesDto){
      return   ResponseEntity.ok(dronesService.registerDrone(dronesDto));
    }
    @GetMapping
    public ResponseEntity<List<DronesDto>> getAllDrone() {
        return ResponseEntity.ok().body(dronesService.getAllDrones());
    }
    @GetMapping("/available")
    public ResponseEntity<List<DronesDto>> checkAvailableDrones(){
        return ResponseEntity.ok().body(dronesService.getAvailableDrones());
    }
    @GetMapping("/checkBatteryBySerial/{serial}")
    public ResponseEntity<Integer> getCapacityForSerial(@PathVariable String serial) {

        return ResponseEntity.ok().body(dronesService.checkDroneBatteryByDroneSerial(serial));
    }

    @PostMapping("/add")
    public ResponseEntity<GeneralResponse> registerDrone1(@RequestBody @Valid DronesDto dronesDto){
        if(!dronesService.getDroneBySerialNumber(dronesDto.getSerialNumber()).isEmpty()){
            return   ResponseEntity.badRequest().body(
                    GeneralResponse.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(dronesDto.getSerialNumber()+" already Exist")
                            .build()
            );
        }
        return   ResponseEntity.ok(
                GeneralResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .data(Map.of("add drone", dronesService.registerDrone(dronesDto)))
                        .message("success")
                        .build()
        );

    }

    @PutMapping("/loadDrone/{droneSerial}")
    public ResponseEntity<String> loadDroneWithMedication(@PathVariable(value = "droneSerial") String droneSerial,@RequestBody List<String> medicationsCodesList){

        return ResponseEntity.ok(dronesService.loading_drone_with_medication(droneSerial,medicationsCodesList));
    }

    @GetMapping("medicationsByDrone/{droneSerial}")
    public ResponseEntity<List<Medications>> checkMedicationsByDrone(@PathVariable(value = "droneSerial") String serialNumber){
        return ResponseEntity.ok(dronesService.checkLoadedMedicationsByDrone(serialNumber));

    }

}
