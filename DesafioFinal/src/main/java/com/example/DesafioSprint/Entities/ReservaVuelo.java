package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.PersonaDTO;
import com.example.DesafioSprint.DTOs.ReservaVueloDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("V")
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

    public ReservaVueloDTO entityToDTO(){
        ArrayList<PersonaDTO> people = new ArrayList<>();
        Persona person = new Persona();
        for(Persona p: getPeople())
            people.add(person.entityToDTO(p));
        return new ReservaVueloDTO(
                getDateFrom(),
                getDateTo(),
                getOrigin(),
                getDestination(),
                getFlightNumber(),
                getSeats(),
                getSeaType(),
                people,getPaymentMethod()
        );
    }

    public ReservaVuelo dtoToEntity(ReservaVueloDTO reservationDTO){
        ArrayList<Persona> people = new ArrayList<>();
        Persona person = new Persona();
        for(PersonaDTO p: reservationDTO.getPeople())
            people.add(person.DTOPersonaToDTO(p));
        ReservaVuelo reservation = new ReservaVuelo();
        reservation.setDateFrom(reservationDTO.getGoingDate());
        reservation.setDateTo(reservationDTO.getReturnDate());
        reservation.setOrigin(reservationDTO.getOrigin());
        reservation.setDestination(reservationDTO.getDestination());
        reservation.setFlightNumber(reservationDTO.getFlightNumber());
        reservation.setSeats(reservationDTO.getSeats());
        reservation.setSeaType(reservationDTO.getSeatType());
        reservation.setPeople(people);
        return reservation;
    }
}
