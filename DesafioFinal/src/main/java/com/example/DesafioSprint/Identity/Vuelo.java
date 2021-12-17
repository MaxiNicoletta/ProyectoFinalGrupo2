package com.example.DesafioSprint.Identity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vuelo {
    private String flightNumber;
    private String origin;
    private String destination;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date dateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date dateTo;
    private String seatType;
    private double pricePerPerson;
}
