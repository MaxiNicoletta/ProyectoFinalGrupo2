package com.example.DesafioSprint.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class FechasException extends Exception {
    private String ERROR;
    private HttpStatus CODE;

    public FechasException() {
        super();
    }

    public FechasException(String ERROR, HttpStatus CODE) {
        this.ERROR = ERROR;
        this.CODE = CODE;
    }
}