package com.drones.dto;

import com.drones.enums.DroneModel;
import com.drones.enums.DroneState;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DronesDto {
    @Size(min =  1, max =  100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Min(0)
    @Max(500)
    private Integer weight;

    @Min(0)
    @Max(100)
    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;
}
