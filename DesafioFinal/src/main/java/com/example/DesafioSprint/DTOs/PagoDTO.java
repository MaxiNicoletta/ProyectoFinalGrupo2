package com.example.DesafioSprint.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {
    private String type;
    private String number;
    private double dues;
}
