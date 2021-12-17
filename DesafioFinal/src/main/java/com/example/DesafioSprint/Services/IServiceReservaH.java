package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.BookingRequestDTO;
import com.example.DesafioSprint.DTOs.BookingResponseDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Exceptions.PersonasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;

import java.util.List;

public interface IServiceReservaH {
    public BookingResponseDTO addReserva(BookingRequestDTO rvHot) throws UbicacionException, PersonasException, HotelesException, FechasException;

    public List<BookingResponseDTO> getReservasHotel(String codHotel) throws HotelesException;

}

