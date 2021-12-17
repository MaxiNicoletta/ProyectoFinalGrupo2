package com.example.DesafioSprint.Entities;

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
}
