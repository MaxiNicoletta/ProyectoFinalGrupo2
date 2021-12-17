package com.example.DesafioSprint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DisponibilidadVuelosDTO {
    private Date dateFrom;
    private Date dateTo;
    private String origin;
    private String destination;
}
