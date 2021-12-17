package com.example.DesafioSprint.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HotelesException extends Exception {
    private String ERROR;
    private HttpStatus CODE;

    public HotelesException() {
        super();
    }

    public HotelesException(String ERROR, HttpStatus CODE) {
        this.ERROR = ERROR;
        this.CODE = CODE;
    }
}