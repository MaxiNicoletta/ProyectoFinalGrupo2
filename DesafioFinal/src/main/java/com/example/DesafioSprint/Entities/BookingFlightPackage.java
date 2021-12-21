package com.example.DesafioSprint.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingFlightPackage {
    private Booking booking;
    private ReservaVuelo flightReservation;
}
