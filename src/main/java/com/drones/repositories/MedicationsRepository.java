package com.drones.repositories;

import com.drones.entities.Drones;
import com.drones.entities.Medications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationsRepository extends JpaRepository<Medications,String> {

    public List<Medications> findByDrones(Drones drones);
}
