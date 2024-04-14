package com.drones.exceptions;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LENGTH_REQUIRED)
@Data
public class DroneSerialCharsExceeded extends RuntimeException{

    private String message;

    public DroneSerialCharsExceeded(String message) {
        super(message);
        this.message=message;

    }

}
