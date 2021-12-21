package com.example.DesafioSprint.DTOs;

import com.example.DesafioSprint.Entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TouristicPackageDTO {
    private int packageNumber;
    private String name;
    private Date creation_date;
    private int clientId;
    private BookingsOrReservationsDTO bookingsOrReservations;
//    private BookingPackageDTO bookingPackage;
//    private BookingFlightPackageDTO bookingFlightPackage;
//    private FlightReservationPackageDTO flightReservationPackage;

}
