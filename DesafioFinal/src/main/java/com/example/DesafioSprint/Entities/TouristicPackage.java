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
    private int packageNumber;
    private String name;
    private Date creation_date;
    private int clientId;
    private BookingsOrReservations bookingsOrReservations;
//    private BookingPackage bookingPackage;
//    private BookingFlightPackage bookingFlightPackage;
//    private FlightReservationPackage flightReservationPackage;


    public TouristicPackageDTO entityToDTO() {
        return new TouristicPackageDTO(getPackageNumber(), getName(), getCreation_date(), getClientId(), getBookingsOrReservations().entityToDTO());
    }

    public TouristicPackage dtoToEntity(TouristicPackageDTO touristicPackageDTO) {
        return new TouristicPackage(touristicPackageDTO.getPackageNumber(),touristicPackageDTO.getName(),touristicPackageDTO.getCreation_date(),touristicPackageDTO.getClientId(),bookingsOrReservations.DTOtoEntity(touristicPackageDTO.getBookingsOrReservations()));
    }


}
