package com.drones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String details;
}
