package com.example.DesafioSprint.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VueloRequestDTO {
    private String flightNumber;
    private String name;
    private String origin;
    private String destination;
    private String seatType;
    private double flightPrice;
    private String goingDate;
    private String returnDate;
}
