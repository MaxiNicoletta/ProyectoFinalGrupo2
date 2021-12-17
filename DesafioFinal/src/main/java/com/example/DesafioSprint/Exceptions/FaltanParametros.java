package com.example.DesafioSprint.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class FaltanParametros extends Exception {
    private String ERROR;
    private HttpStatus CODE;

    public FaltanParametros() {
        super();
    }

    public FaltanParametros(String ERROR, HttpStatus CODE) {
        this.ERROR = ERROR;
        this.CODE = CODE;
    }

}
