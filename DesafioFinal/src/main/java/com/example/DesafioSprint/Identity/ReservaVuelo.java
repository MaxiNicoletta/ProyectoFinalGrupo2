package com.example.DesafioSprint.Identity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaVuelo extends Reserva {
    private String flightNumber;
    private int seats;
    private String seaType;
    private String origin;

    public ReservaVuelo(String userName, Date dateFrom, Date dateTo, String destination, List<Persona> people, Pago paymentMethod, double amount, double interest, double total, String flightNumber, int seats, String seaType, String origin) {
        super(userName, dateFrom, dateTo, destination, people, paymentMethod, amount, interest, total);
        this.flightNumber = flightNumber;
        this.seats = seats;
        this.seaType = seaType;
        this.origin = origin;
    }

}
