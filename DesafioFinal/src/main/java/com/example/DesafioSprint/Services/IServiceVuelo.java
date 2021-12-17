package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadVuelosDTO;
import com.example.DesafioSprint.DTOs.VueloDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Exceptions.VuelosException;

import java.util.List;

public interface IServiceVuelo {
    List<VueloDTO> getVuelos() throws VuelosException;

    boolean existsVuelo(String cod);

    boolean existsDestinationVuelo(String dest);

    boolean existsOriginVuelo(String org);

    List<VueloDTO> disponibilidadVuelos(DisponibilidadVuelosDTO vuelo) throws UbicacionException, FechasException, VuelosException;
}
