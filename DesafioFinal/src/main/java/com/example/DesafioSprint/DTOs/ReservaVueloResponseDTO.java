package com.example.DesafioSprint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservaVueloResponseDTO {
    private String userName;
    private double amount;
    private double interest;
    private double total;
    private ReservaVueloDTO flightReservation;
    private StatusDTO statusCode;
}
