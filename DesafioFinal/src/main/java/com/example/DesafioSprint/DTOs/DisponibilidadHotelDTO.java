package com.example.DesafioSprint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DisponibilidadHotelDTO {
    private Date dateFrom;
    private Date dateTo;
    private String destination;

}
