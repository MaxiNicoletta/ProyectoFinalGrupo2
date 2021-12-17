package com.example.DesafioSprint.Identity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {
    private String type;
    private String number;
    private double dues;
}
