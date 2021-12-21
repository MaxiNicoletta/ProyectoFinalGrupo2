//package com.example.DesafioSprint.Entities;
//
//import com.example.DesafioSprint.DTOs.BookingDTO;
//import com.example.DesafioSprint.DTOs.BookingFlightPackageDTO;
//import com.example.DesafioSprint.DTOs.BookingPackageDTO;
//import com.example.DesafioSprint.DTOs.ReservaVueloDTO;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class BookingPackage {
//    private Booking firstBooking;
//    private Booking secondBooking;
//
//    public BookingPackageDTO packageToDTO(){
//        BookingDTO bookingDTO = firstBooking.bookingToDTO();
//        BookingDTO secondbBookingDTO = secondBooking.bookingToDTO();
//        return new BookingPackageDTO(bookingDTO,secondbBookingDTO);
//    }
//
//    public BookingPackage DTOtoPackage(BookingDTO firstBookingDTO, BookingDTO secondBookingDTO){
//        Booking booking = new Booking();
//        Booking secondBooking = new Booking();
//        booking.bookingDTOtoBooking(firstBookingDTO);
//        booking.bookingDTOtoBooking(secondBookingDTO);
//        return new BookingPackage(booking,secondBooking);
//    }

//
//}
