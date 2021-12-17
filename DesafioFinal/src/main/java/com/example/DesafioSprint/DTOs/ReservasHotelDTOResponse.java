package com.example.DesafioSprint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservasHotelDTOResponse {
    private String userName;
    private double amount;
    private double interest;
    private double total;
    private ReservaDTO booking;
    private StatusDTO statusCode;
}
