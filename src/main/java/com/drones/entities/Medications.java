package com.drones.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medications {
    @Id
    @Pattern(
            regexp = "[A-Z0-9_]+",
            message = "allowed only upper case letters, underscore and numbers"
    )
    private String code;



    @Column(nullable = false)
    @Pattern(
            regexp = "[a-zA-Z_0-9-]+",
            message = "allowed only letters, numbers, ‘-‘, ‘_’"
    )
    private String name;



    @Column (nullable = false)
    private Integer weight;


    @Column
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_serial")
    private Drones drones;
}
