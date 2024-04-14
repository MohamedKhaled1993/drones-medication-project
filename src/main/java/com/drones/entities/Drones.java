package com.drones.entities;

import com.drones.enums.DroneModel;
import com.drones.enums.DroneState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "drones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drones {

    @Id
    @Size(min =  1, max =  100)
    private String serialNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Column (nullable = false)
    @Min(0)
    @Max(500)
    private Integer weight;

    @Column (nullable = false)
    @Min(0)
    @Max(100)
    private Integer batteryCapacity;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private DroneState state;







}
