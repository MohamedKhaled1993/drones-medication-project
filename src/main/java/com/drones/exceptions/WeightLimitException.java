package com.drones.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Data
public class WeightLimitException extends RuntimeException{

    private String message;
    public WeightLimitException(String message) {
        super(message);
        this.message=message;

    }
}
