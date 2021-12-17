package com.example.DesafioSprint.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class VuelosException extends Exception {
    private String ERROR;
    private HttpStatus CODE;

    public VuelosException() {
        super();
    }

    public VuelosException(String ERROR, HttpStatus CODE) {
        this.ERROR = ERROR;
        this.CODE = CODE;
    }
}