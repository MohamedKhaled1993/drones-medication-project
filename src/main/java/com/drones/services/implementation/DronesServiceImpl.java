package com.drones.services.implementation;

import com.drones.dto.DronesDto;
import com.drones.entities.Drones;
import com.drones.entities.Medications;
import com.drones.enums.DroneState;
import com.drones.exceptions.*;
import com.drones.repositories.DronesRepository;
import com.drones.repositories.MedicationsRepository;
import com.drones.services.DronesService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.drones.utilities.Constants.DRONE_FLEET_LIMIT;
import static com.drones.utilities.Constants.WEIGHT_LIMIT;


@Service
@Slf4j
public class DronesServiceImpl implements DronesService {
    private final DronesRepository dronesRepository;
    private final ModelMapper modelMapper;

    private final MedicationsRepository medicationsRepository;

    public DronesServiceImpl(DronesRepository dronesRepository,MedicationsRepository medicationsRepository ,ModelMapper modelMapper) {
        this.dronesRepository = dronesRepository;
        this.medicationsRepository=medicationsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DronesDto registerDrone(DronesDto dronesDto) {

        if(!dronesRepository.findById(dronesDto.getSerialNumber()).isEmpty()){
            throw new ExistedResourceException("serial number "+dronesDto.getSerialNumber()+" already exists");
        }
        log.info("starting register new drone ..");
        if (dronesRepository.count() == DRONE_FLEET_LIMIT) {
            throw new DroneFeetSizeExceeded("Drone Fleet Size Exceeded");
        }

        log.info("check drone Serial Number Length..");

        if(dronesDto.getSerialNumber().length()>100){
            throw new DroneSerialCharsExceeded("Drone Serial number characters limit exceeded");
        }
        Drones drone = mapToEntity(dronesDto);
        drone.setState(DroneState.IDLE);
        log.info("Counts "+ dronesRepository.count());
        log.info("save new drone ..");

        dronesRepository.save(drone);
        return mapToDto(drone);
    }

    @Override
    public List<DronesDto> getAllDrones() {
        List<Drones> dronesList= dronesRepository.findAll();
        return dronesList.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Integer checkDroneBatteryByDroneSerial(String serial) {
        log.info("check Drone Battery By Drone Serial ..");

        Drones drones=dronesRepository.findById(serial).orElseThrow(()->new ResourceNotFoundException(serial));
        return drones.getBatteryCapacity();
    }

    @Override
    public List<Medications> checkLoadedMedicationsByDrone(String droneSerialNum) {
        log.info("check Loaded Medications By Drone ..");

        Drones drones=dronesRepository.findById(droneSerialNum).orElseThrow(()->new ResourceNotFoundException(droneSerialNum));
        return medicationsRepository.findByDrones(drones);
    }

    @Override
    public List<DronesDto> getAvailableDrones() {
        log.info("get available ready drones ..");
        List<Drones> availableDrones=dronesRepository.findDronesByState();
        return availableDrones.stream().filter(drone ->drone.getBatteryCapacity()>=25 && drone.getWeight()<WEIGHT_LIMIT).map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Drones> getDroneBySerialNumber(String serialNumber) {
        log.info("get drones by serial number = "+serialNumber);
        return dronesRepository.findById(serialNumber);
    }

    @Override
    @Transactional
    public String loading_drone_with_medication(String serialNumber, List<String> medicationCodeList) {
        log.info("starting loading drone "+serialNumber+" with list of medications");
           Drones drones =dronesRepository.findById(serialNumber).orElseThrow(()->new ResourceNotFoundException(serialNumber));
           medicationCodeList.forEach(code ->{
               Medications medications =medicationsRepository.findById(code).orElseThrow(()->new ResourceNotFoundException(code) );
                 int finalWeight = drones.getWeight() + medications.getWeight();
               log.info("the total weight of drone when add current medication is "+ finalWeight);

          log.info("starting check drone availability ..");
               checkDroneAvailability(drones,finalWeight);

               medications.setDrones(drones);
               log.info("load an item to drone...");
               medicationsRepository.save(medications);
               if(finalWeight <WEIGHT_LIMIT){
                   drones.setWeight(finalWeight);
                   drones.setState(DroneState.LOADING);
               }
               if(finalWeight==WEIGHT_LIMIT){
                   drones.setWeight(finalWeight);
                   drones.setState(DroneState.LOADED);
               }

           });
           log.info("save drone with the new weight and state after loading");
           dronesRepository.save(drones);
        return "Drone Loaded";
    }

//

    public void checkDroneAvailability(Drones drones,Integer finalWeight){
        log.info("check total weight,state and battery capacity for drone before loading a medication");
        if (finalWeight >WEIGHT_LIMIT){
            throw new WeightLimitException("Drone Weight Limit Exceeded ");
        }
        if(drones.getState()==DroneState.LOADED || drones.getState()==DroneState.DELIVERING ||drones.getState()==DroneState.DELIVERED){
            throw new NotReadyDroneException("Drone Not Ready as it's States is LOADED or DELIVERING OR DELIVERED");
        }
        if(drones.getBatteryCapacity()<=25){
            throw new BatteryLimitException("Drone Battery Low");
        }


    }
    @Scheduled(fixedRate = 60000)
    public void checkBatteryLevels() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm");

        log.info("Drone BatteryCapacity check time at "+ formatter.format(LocalDateTime.now()));

        List<DronesDto> drones = getAllDrones();

        for (DronesDto drone : drones) {

            log.info("Drone "+drone.getSerialNumber()+" Battery Capacity "+drone.getBatteryCapacity());
        }
    }
    private DronesDto mapToDto(Drones drones)
    {

        return modelMapper.map(drones,DronesDto.class);
    }
    private Drones mapToEntity(DronesDto dronesDto)
    {

        return modelMapper.map(dronesDto,Drones.class);
    }


}
