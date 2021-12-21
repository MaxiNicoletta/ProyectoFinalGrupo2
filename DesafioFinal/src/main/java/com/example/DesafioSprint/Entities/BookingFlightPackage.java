package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.BookingDTO;
import com.example.DesafioSprint.DTOs.BookingFlightPackageDTO;
import com.example.DesafioSprint.DTOs.ReservaVueloDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BookingFlightPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "bookingFlightPackage")
    private Booking booking;
    @OneToOne(mappedBy = "bookingFlightPackage")
    private ReservaVuelo flightReservation;
    @ManyToMany(mappedBy = "bookingFlightPackages", cascade = CascadeType.ALL)
    private List<TouristicPackage> touristicPackagesFH;

    public BookingFlightPackageDTO packageToDTO(){
        BookingDTO bookingDTO = booking.bookingToDTO();
        ReservaVueloDTO flightReservationDTO = flightReservation.entityToDTO();
        return new BookingFlightPackageDTO(bookingDTO,flightReservationDTO);
    }


}
