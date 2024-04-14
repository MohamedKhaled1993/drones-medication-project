package com.drones.repositories;

import com.drones.entities.Drones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DronesRepository extends JpaRepository<Drones,String> {

    @Query("SELECT d FROM Drones d where d.state ='IDLE' or d.state='LOADING' ")
    public List<Drones> findDronesByState();

//    @Query("SELECT  d.batteryCapacity FROM Drones d WHERE d.serialNumber = :serialNumber")
//    public Integer checkBatteryCapacityBySerial(@Param("serialNumber") String serialNumber);


}
