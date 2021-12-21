//package com.example.DesafioSprint.Entities;
//
//import com.example.DesafioSprint.DTOs.BookingDTO;
//import com.example.DesafioSprint.DTOs.BookingFlightPackageDTO;
//import com.example.DesafioSprint.DTOs.ReservaVueloDTO;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class BookingFlightPackage {
//    private Booking booking;
//    private ReservaVuelo flightReservation;
//
//    public BookingFlightPackageDTO packageToDTO(){
//        BookingDTO bookingDTO = booking.bookingToDTO();
//        ReservaVueloDTO flightReservationDTO = flightReservation.entityToDTO();
//        return new BookingFlightPackageDTO(bookingDTO,flightReservationDTO);
//    }
//
//    public BookingFlightPackage DTOtoPackage(BookingDTO bookingDTO, ReservaVueloDTO flightReservationDTO){
//        Booking booking = new Booking();
//        booking.bookingDTOtoBooking(bookingDTO);
//        ReservaVuelo flightReservation = new ReservaVuelo();
//        flightReservation.dtoToEntity(flightReservationDTO);
//        return new BookingFlightPackage(booking,flightReservation);
//    }
//
//}
