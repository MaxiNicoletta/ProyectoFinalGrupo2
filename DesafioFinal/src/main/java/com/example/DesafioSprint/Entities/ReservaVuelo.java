package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.ReservaVueloDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

//    public ReservaVueloDTO flightReservationToDTO(){
//        return new ReservaVueloDTO(
//                getDateFrom(),
//                getDateTo(),
//                getOrigin(),
//                getDestination(),
//                getFlightNumber(),
//                getUserName(),
//                getAmount(),
//                getInterest(),
//                getTotal(),
//                getSeats(),
//                getSeaType(),
//                getOrigin()
//        );
//    }
//    public ReservaVuelo flightReservationDTOtoFlight(ReservaVueloDTO reserva){
//        return new ReservaVuelo(
//                reserva.getDateFrom(),
//                reserva.getDateTo(),
//                reserva.getOrigin(),
//                reserva.getDestination(),
//                reserva.getFlightNumber(),
//                reserva.getName(),
//                reserva.getSeats(),
//                reserva.getSeatType(),
//                reserva.getPeople()
//        );
//    }
}
