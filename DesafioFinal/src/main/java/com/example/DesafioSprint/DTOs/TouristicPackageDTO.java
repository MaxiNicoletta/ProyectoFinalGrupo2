package com.example.DesafioSprint.DTOs;

import com.example.DesafioSprint.Entities.Booking;
import com.example.DesafioSprint.Entities.ReservaVuelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TouristicPackageDTO {
    private int packageNumber;
    private String name;
    private Date creation_date;
    private int clientId;
    private Booking booking;
    private ReservaVuelo reservaVuelo;


}
