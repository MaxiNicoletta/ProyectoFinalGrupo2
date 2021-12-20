package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.VueloDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@DiscriminatorValue("V")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String flightNumber;
    private String name;
    private String origin;
    private String destination;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date goingDate;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date returnDate;
    private String seatType;
    private double pricePerPerson;
    @OneToMany(mappedBy = "flightNumber")
    List<ReservaVuelo> flightReservations;

    public Vuelo(String flightNumber, String name, String origin, String destination, Date goingDate, Date returnDate, String seatType, double pricePerPerson) {
        this.flightNumber = flightNumber;
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.goingDate = goingDate;
        this.returnDate = returnDate;
        this.seatType = seatType;
        this.pricePerPerson = pricePerPerson;
    }


    public VueloDTO entityToDTO(){
        return new VueloDTO(
                getFlightNumber(),
                getName(),
                getOrigin(),
                getDestination(),
                getSeatType(),
                getPricePerPerson(),
                getGoingDate(),
                getReturnDate()
        );
    }

    public Vuelo dtoToEntity(VueloDTO vueloDTO){
        return new Vuelo(
                vueloDTO.getFlightNumber(),
                vueloDTO.getName(),
                vueloDTO.getOrigin(),
                vueloDTO.getDestination(),
                vueloDTO.getGoingDate(),
                vueloDTO.getReturnDate(),
                vueloDTO.getSeatType(),
                vueloDTO.getFlightPrice()
        );
    }
}
