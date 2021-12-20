package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.FlightResponseDTO;
import com.example.DesafioSprint.DTOs.ReservaVueloDTO;
import com.example.DesafioSprint.DTOs.ReservasVueloRequestDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.PersonasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Exceptions.VuelosException;

import java.util.ArrayList;


public interface IServiceReservaV {

    FlightResponseDTO addReserva(ReservasVueloRequestDTO rsVuelo) throws PersonasException, VuelosException, FechasException, UbicacionException;

    ArrayList<ReservaVueloDTO> getAllReservations();

    FlightResponseDTO updateFlightReservation(Long id, ReservasVueloRequestDTO reservasVueloRequestDTO) throws VuelosException, PersonasException;

    FlightResponseDTO deleteFlightReservation(Long id) throws VuelosException;
}
