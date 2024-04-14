package com.drones.repositories;

import com.drones.entities.Drones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DronesRepository extends JpaRepository<Drones,String> {

    @Query("SELECT drone FROM Drones drone where drone.state ='IDLE' or drone.state='LOADING' ")
     List<Drones> findDronesByState();




}
