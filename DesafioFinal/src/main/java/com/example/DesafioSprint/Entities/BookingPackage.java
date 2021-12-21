package com.example.DesafioSprint.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingPackage {
    private Booking firstBooking;
    private Booking secondBooking;
}
