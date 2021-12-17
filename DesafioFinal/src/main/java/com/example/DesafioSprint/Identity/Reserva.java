package com.example.DesafioSprint.Identity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    protected String userName;
    protected Date dateFrom;
    protected Date dateTo;
    protected String destination;
    protected List<Persona> people;
    protected Pago paymentMethod;
    protected double amount;
    protected double interest;
    protected double total;
}


