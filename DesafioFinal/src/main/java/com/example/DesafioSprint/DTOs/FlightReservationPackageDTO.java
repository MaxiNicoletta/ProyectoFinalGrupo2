package com.example.DesafioSprint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightReservationPackageDTO {
    private ReservaVueloDTO flightReservationDTO;
    private ReservaVueloDTO secondFlightReservationDTO;
}
