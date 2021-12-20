package com.example.DesafioSprint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BookingResponseDTO {
//    private String userName;
//    private double amount;
//    private double interest;
//    private double total;
//    private ReservaDTO booking;
//    private StatusDTO statusCode;
    private String message;
    public BookingResponseDTO(String message){
        message = "Booking" + message;
    }



}
