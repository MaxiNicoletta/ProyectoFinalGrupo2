package com.example.DesafioSprint.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TouristicPackageRequestDTO {
    private int packageNumber;
    private String name;
    private String creation_date;
    private int clientId;
    private BookingsOrReservationsDTO bookingsOrReservations;
}
