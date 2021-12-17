package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadHotelDTO;
import com.example.DesafioSprint.DTOs.ListHotelesDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;

public interface IServiceHotel {
    public ListHotelesDTO getHoteles() throws HotelesException;

    public ListHotelesDTO getHotelesDisponibles(DisponibilidadHotelDTO hotel) throws HotelesException, FechasException;

    public boolean existsHoteles(String cod);

    public boolean existsDestination(String dest);

}
