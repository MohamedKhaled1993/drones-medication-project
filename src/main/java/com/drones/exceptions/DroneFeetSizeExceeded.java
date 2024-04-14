package com.drones.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Data
public class DroneFeetSizeExceeded extends RuntimeException{
    private String message;
    public DroneFeetSizeExceeded(String message) {
        super(message);
        this.message=message;

    }
}
