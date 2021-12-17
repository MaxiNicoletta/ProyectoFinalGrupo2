package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadVuelosDTO;
import com.example.DesafioSprint.DTOs.VueloDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Exceptions.VuelosException;

import java.util.List;

public interface IServiceVuelo {
    FlightsResponseDTO addFlight(VueloDTO flightDTO);
    List<VueloDTO> getFlights() throws VuelosException;
    List<VueloDTO> disponibilidadVuelos(DisponibilidadVuelosDTO vuelo) throws UbicacionException, FechasException, VuelosException;
    FlightsResponseDTO updateFlight(String cod, VueloDTO flightDto) throws  VuelosException;
    FlightsResponseDTO deleteFlight(String cod) throws  VuelosException;
    }
