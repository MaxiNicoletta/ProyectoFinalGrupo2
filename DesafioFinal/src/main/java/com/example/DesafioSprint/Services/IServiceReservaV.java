package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.ReservaVueloResponseDTO;
import com.example.DesafioSprint.DTOs.ReservasVueloRequestDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.PersonasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Exceptions.VuelosException;

import java.util.List;

public interface IServiceReservaV {
    ReservaVueloResponseDTO addReserva(ReservasVueloRequestDTO rsVuelo) throws PersonasException, VuelosException, FechasException, UbicacionException;

    public List<ReservaVueloResponseDTO> getReservasVuelo(String codVuelo) throws VuelosException;

}
