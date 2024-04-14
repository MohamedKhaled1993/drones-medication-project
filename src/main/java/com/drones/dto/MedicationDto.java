package com.drones.dto;

import com.drones.entities.Drones;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDto {


    @Pattern(
            regexp = "[a-zA-Z_0-9-]+",
            message = "allowed only letters, numbers, ‘-‘, ‘_’"
    )
    private String name;

    private Integer weight;

    @Pattern(
            regexp = "[A-Z0-9_]+",
            message = "allowed only upper case letters, underscore and numbers"
    )
    private String code;
    private String image;

    private Drones drones;
}
