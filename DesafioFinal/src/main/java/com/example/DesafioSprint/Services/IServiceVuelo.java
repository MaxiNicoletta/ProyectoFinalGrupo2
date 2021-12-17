package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadVuelosDTO;
import com.example.DesafioSprint.DTOs.FlightResponseDTO;
import com.example.DesafioSprint.DTOs.VueloDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Exceptions.VuelosException;

import java.util.List;

public interface IServiceVuelo {
    FlightResponseDTO addFlight(VueloDTO flightDTO)throws FechasException,VuelosException;
    List<VueloDTO> getFlights() throws VuelosException;
    List<VueloDTO> disponibilidadVuelos(DisponibilidadVuelosDTO vuelo) throws UbicacionException, FechasException, VuelosException;
    FlightResponseDTO updateFlight(String cod, VueloDTO flightDto) throws  VuelosException;
    FlightResponseDTO deleteFlight(String cod) throws VuelosException;
    }
