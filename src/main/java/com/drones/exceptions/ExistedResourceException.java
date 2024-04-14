package com.drones.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Data
public class ExistedResourceException extends RuntimeException{

        private String message;
        public ExistedResourceException(String message ) {
            super(String.format("%s already existed ", message));
            this.message=message;

        }
}
