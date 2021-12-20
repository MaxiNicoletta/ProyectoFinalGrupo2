package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.BookingDTO;
import com.example.DesafioSprint.DTOs.BookingRequestDTO;
import com.example.DesafioSprint.DTOs.BookingResponseDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Exceptions.PersonasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Services.IServiceBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
    @Autowired
    IServiceBooking hotelBooking;

    @PostMapping("/bookings/new")
    public ResponseEntity<BookingResponseDTO> addReserva(@Valid @RequestBody BookingRequestDTO bookingRequestDTO) throws PersonasException, HotelesException, FechasException, UbicacionException {
        return new ResponseEntity<>(hotelBooking.addBooking(bookingRequestDTO), HttpStatus.OK);
    }

    @PutMapping("/hotel-bookings/edit/?id=num_id")
    public ResponseEntity<BookingResponseDTO> modifyBooking(@RequestBody BookingRequestDTO bookingRequestDTO){
        return new ResponseEntity<>(hotelBooking.updateBooking(bookingRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/hotel-bookings/edit/?id=num_id")
    public ResponseEntity<BookingResponseDTO> deleteBooking(@RequestBody Long id){
        return new ResponseEntity<>(hotelBooking.deleteBooking(id), HttpStatus.OK);
    }

    @GetMapping("/hotel-bookings")
    ResponseEntity<List<BookingDTO>> getBookings() throws HotelesException {
        return new ResponseEntity<>(hotelBooking.getBookings(), HttpStatus.OK);
    }




}
