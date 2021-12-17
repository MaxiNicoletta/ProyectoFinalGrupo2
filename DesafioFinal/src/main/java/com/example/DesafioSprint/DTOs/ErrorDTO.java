package com.example.DesafioSprint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private String nombre;
    private Map<String, String> descripcion;
    private HttpStatus statuscode;
}
