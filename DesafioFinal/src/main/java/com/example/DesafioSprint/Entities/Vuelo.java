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
@DiscriminatorValue("V")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String flightNumber;
    private String name;
    private String origin;
    private String destination;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date dateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date dateTo;
    private String seatType;
    private double pricePerPerson;
    @OneToMany(mappedBy = "flightNumber")
    List<ReservaVuelo> flightReservations;

    //Constructor

    public Vuelo(String flightNumber, String name, String origin, String destination, Date dateFrom, Date dateTo, String seatType, double pricePerPerson) {
        this.flightNumber = flightNumber;
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.seatType = seatType;
        this.pricePerPerson = pricePerPerson;
    }

    public VueloDTO flightToDTO(){
        return new VueloDTO(
                getFlightNumber(),
                getName(),
                getOrigin(),
                getDestination(),
                getDateFrom(),
                getDateTo(),
                getSeatType(),
                getPricePerPerson()
        );
    }
    public Vuelo flightDTOtoFlight(VueloDTO vueloDTO){
        return new Vuelo( vueloDTO.getFlightNumber(),
                vueloDTO.getName(),
                vueloDTO.getOrigin(),
                vueloDTO.getDestination(),
                vueloDTO.getDateFrom(),
                vueloDTO.getDateTo(),
                vueloDTO.getSeatType(),
                vueloDTO.getPricePerPerson()
        );
    }
}
