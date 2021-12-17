package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.ReservaHotelRequestDTO;
import com.example.DesafioSprint.DTOs.ReservasHotelDTOResponse;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Exceptions.PersonasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;

import java.util.List;

public interface IServiceReservaH {
    public ReservasHotelDTOResponse addReserva(ReservaHotelRequestDTO rvHot) throws UbicacionException, PersonasException, HotelesException, FechasException;

    public List<ReservasHotelDTOResponse> getReservasHotel(String codHotel) throws HotelesException;

}

