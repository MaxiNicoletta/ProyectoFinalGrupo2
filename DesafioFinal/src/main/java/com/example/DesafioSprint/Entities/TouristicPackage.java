package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.TouristicPackageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouristicPackage {
    private Long packageNumber;
    private String name;
    private Date creation_date;
    private int clientId;
    private BookingsOrReservations bookingsOrReservations;
//    private BookingPackage bookingPackage;
//    private BookingFlightPackage bookingFlightPackage;
//    private FlightReservationPackage flightReservationPackage;



    //    public TouristicPackageDTO entityToDTO(){
//        return new TouristicPackageDTO(getBooking().bookingToDTO(),getReservaVuelo().entityToDTO());
//    }
//
//    public TouristicPackage dtoToEntity(TouristicPackageDTO touristicPackageDTO){
//        return new TouristicPackage();
//    }

}
