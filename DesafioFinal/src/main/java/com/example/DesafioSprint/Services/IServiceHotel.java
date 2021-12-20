package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadHotelDTO;
import com.example.DesafioSprint.DTOs.HotelDTO;
import com.example.DesafioSprint.DTOs.HotelResponseDTO;
import com.example.DesafioSprint.DTOs.ListHotelesDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Exceptions.VuelosException;

public interface IServiceHotel {
    ListHotelesDTO getHoteles() throws HotelesException;
    HotelResponseDTO addHotel(HotelDTO hotelDTO)throws FechasException, VuelosException;
    HotelResponseDTO updateHotel(HotelDTO hotelDTO) throws  HotelesException;
    public ListHotelesDTO getHotelesDisponibles(DisponibilidadHotelDTO hotel) throws HotelesException, FechasException;
    HotelResponseDTO deleteHotel(String cod)throws HotelesException ;

}
