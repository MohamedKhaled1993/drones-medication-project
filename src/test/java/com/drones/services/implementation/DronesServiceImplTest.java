package com.drones.services.implementation;

import com.drones.dto.DronesDto;
import com.drones.entities.Drones;
import com.drones.enums.DroneModel;
import com.drones.enums.DroneState;
import com.drones.repositories.DronesRepository;
import com.drones.repositories.MedicationsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class DronesServiceImplTest {

    @Mock
    private DronesRepository dronesRepository;
    @Mock
    private MedicationsRepository medicationsRepository;
    @Mock
    private ModelMapper modelMapper;


    @InjectMocks
    private DronesServiceImpl dronesService ;

    @BeforeEach
    public void init() {
        new DronesServiceImpl(dronesRepository,medicationsRepository,modelMapper);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerDrone() {
        // Given
        DronesDto dronesDto = new DronesDto("serial1", DroneModel.LIGHTWEIGHT,100,40, DroneState.IDLE);
        Drones drones = new Drones("serial1", DroneModel.LIGHTWEIGHT,100,40, DroneState.IDLE);

        Mockito.when(modelMapper.map(dronesDto, Drones.class)).thenReturn(drones);
        Mockito.when(dronesRepository.save(drones)).thenReturn(drones);

        // When
        Drones addedDrone = dronesService.registerDrone(dronesDto);

        // Then
        assertEquals(drones.getSerialNumber(), addedDrone.getSerialNumber());
        assertEquals(drones.getWeight(), addedDrone.getWeight());
        assertEquals(drones.getState(), addedDrone.getState());
        assertEquals(drones.getBatteryCapacity(), addedDrone.getBatteryCapacity());
        assertEquals(drones.getBatteryCapacity(), addedDrone.getBatteryCapacity());

        // Verify that drone was added to the repository
        Mockito.verify(dronesRepository, Mockito.times(1)).save(Mockito.any());

    }
}
