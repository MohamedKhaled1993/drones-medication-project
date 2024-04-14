package com.drones.services;

import com.drones.dto.DronesDto;
import com.drones.entities.Drones;
import com.drones.entities.Medications;

import java.util.List;

public interface DronesService {

     Drones registerDrone(DronesDto dronesDto);

     List<DronesDto> getAllDrones();

     Integer checkDroneBatteryByDroneSerial(String serial);
     List<DronesDto> getAvailableDrones();


//    public Optional<Drones> getDroneBySerialNumber(String serialNumber);

     String loading_drone_with_medication(String serialNumber,List<String> medicationCodeList );

     List<Medications> checkLoadedMedicationsByDrone(String droneSerial);
}
