package com.drones.services;

import com.drones.dto.DronesDto;
import com.drones.entities.Drones;
import com.drones.entities.Medications;

import java.util.List;
import java.util.Optional;

public interface DronesService {

    public DronesDto registerDrone(DronesDto dronesDto);

    public List<DronesDto> getAllDrones();

    public Integer checkDroneBatteryByDroneSerial(String serial);
    public List<DronesDto> getAvailableDrones();

//    public Integer checkBatteryBySerial(String serialNumber);
    public Optional<Drones> getDroneBySerialNumber(String serialNumber);

    public String loading_drone_with_medication(String serialNumber,List<String> medicationCodeList );

    public List<Medications> checkLoadedMedicationsByDrone(String droneSerial);
}
