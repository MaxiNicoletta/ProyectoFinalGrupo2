package com.example.DesafioSprint.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PersonasException extends Exception {
    private String ERROR;
    private HttpStatus CODE;

    public PersonasException() {
        super();
    }

    public PersonasException(String ERROR, HttpStatus CODE) {
        this.ERROR = ERROR;
        this.CODE = CODE;
    }
}