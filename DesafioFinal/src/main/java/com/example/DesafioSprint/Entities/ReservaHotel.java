package com.example.DesafioSprint.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("H")
public class ReservaHotel extends Reserva {
    private String hotelCode;
    private int peopleAmount;
    private String roomType;

    public ReservaHotel(String userName, Date dateFrom, Date dateTo, String destination, List<Persona> people, Pago paymentMethod, double amount, double interest, double total, String hotelCode, int peopleAmount, String roomType) {
        super(userName, dateFrom, dateTo, destination, people, paymentMethod, amount, interest, total);
        this.hotelCode = hotelCode;
        this.peopleAmount = peopleAmount;
        this.roomType = roomType;
    }


}
