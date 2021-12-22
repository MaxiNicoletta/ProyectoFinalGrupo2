package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.BookingDTO;
import com.example.DesafioSprint.DTOs.BookingRequestDTO;
import com.example.DesafioSprint.DTOs.BookingResponseDTO;
import com.example.DesafioSprint.Entities.Booking;
import com.example.DesafioSprint.Entities.Persona;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Exceptions.PersonasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IServiceBooking {

     BookingResponseDTO addBooking(BookingRequestDTO bookingRequestDTO) throws UbicacionException, PersonasException, HotelesException, FechasException;

     List<BookingDTO> getBookings() throws HotelesException;

     BookingResponseDTO deleteBooking(Long id);

     BookingResponseDTO updateBooking(Long id,BookingRequestDTO bookingRequestDTO) throws HotelesException, FechasException, PersonasException;





}

