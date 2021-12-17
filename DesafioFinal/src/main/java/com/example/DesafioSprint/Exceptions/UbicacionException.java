package com.example.DesafioSprint.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UbicacionException extends Exception {
    private String ERROR;
    private HttpStatus CODE;

    public UbicacionException() {
        super();
    }

    public UbicacionException(String ERROR, HttpStatus CODE) {
        this.ERROR = ERROR;
        this.CODE = CODE;
    }
}