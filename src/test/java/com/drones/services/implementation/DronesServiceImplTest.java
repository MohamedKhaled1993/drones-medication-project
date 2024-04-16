package com.drones.services.implementation;

import com.drones.dto.DronesDto;
import com.drones.entities.Drones;
import com.drones.enums.DroneModel;
import com.drones.enums.DroneState;
import com.drones.repositories.DronesRepository;
import com.drones.repositories.MedicationsRepository;
import com.drones.services.DronesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
class DronesServiceImplTest {

    @Mock
    private DronesRepository dronesRepository;
    @Mock
    private MedicationsRepository medicationsRepository;
    @Mock
    private ModelMapper modelMapper;


    @InjectMocks
    private DronesService dronesService ;

    @BeforeEach
    public void setUp() {
        dronesRepository = mock(DronesRepository.class);
        modelMapper = mock(ModelMapper.class);
        dronesService= new DronesServiceImpl(dronesRepository,medicationsRepository,modelMapper);
    }

    @Test
    void registerDrone() {

        // Given
        DronesDto dronesDto = new DronesDto("serial1", DroneModel.LIGHTWEIGHT,100,40, DroneState.IDLE);
        Drones drones = new Drones("serial1", DroneModel.LIGHTWEIGHT,100,40, DroneState.IDLE);

        // When
        Mockito.when(modelMapper.map(dronesDto, Drones.class)).thenReturn(drones);
        Mockito.when(dronesRepository.save(drones)).thenReturn(drones);

        //result
        Drones addedDrone = dronesService.registerDrone(dronesDto);

        // Then
        assertEquals(drones.getSerialNumber(), addedDrone.getSerialNumber());
        assertEquals(drones.getWeight(), addedDrone.getWeight());
        assertEquals(drones.getState(), addedDrone.getState());
        assertEquals(drones.getBatteryCapacity(), addedDrone.getBatteryCapacity());
        assertEquals(drones.getBatteryCapacity(), addedDrone.getBatteryCapacity());

        //or
//        assertEquals(drones,addedDrone);

        // Verify that drone was added to the repository
        Mockito.verify(dronesRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    void getAllDrones(){

        //mocking
        List<Drones> dronesList =getDronesList();
        Mockito.when(dronesRepository.findAll()).thenReturn(dronesList);

        List<DronesDto> dronesDtoList=getDronesDtoList();
        Mockito.when((modelMapper.map(dronesList.get(0), DronesDto.class))).thenReturn(dronesDtoList.get(0));
        Mockito.when((modelMapper.map(dronesList.get(1), DronesDto.class))).thenReturn(dronesDtoList.get(1));

        //result with service
        List<DronesDto> resultDTOList = dronesService.getAllDrones();

        // Verify the result
        List<DronesDto> expectedDTOList = dronesDtoList;
        assertEquals(expectedDTOList, resultDTOList);

        // Verify that the methods of the mocks were called with the correct arguments
        Mockito.verify(dronesRepository).findAll();
        Mockito.verify(modelMapper).map(dronesList.get(0), DronesDto.class);
        Mockito.verify(modelMapper).map(dronesList.get(1), DronesDto.class);



    }
    private List<Drones> getDronesList(){
        List<Drones> dronesList =new ArrayList<>();
        dronesList.add(new Drones("serial1",DroneModel.CRUISERWEIGHT,50,30,DroneState.IDLE));
        dronesList.add(new Drones("serial2",DroneModel.LIGHTWEIGHT,30,70,DroneState.IDLE));
        return dronesList;

    }
    private List<DronesDto> getDronesDtoList(){
        List<DronesDto> dronesListDto =new ArrayList<>();
        dronesListDto.add(new DronesDto("serial1",DroneModel.CRUISERWEIGHT,50,30,DroneState.IDLE));
        dronesListDto.add(new DronesDto("serial2",DroneModel.LIGHTWEIGHT,30,70,DroneState.IDLE));
        return dronesListDto;

    }
}
