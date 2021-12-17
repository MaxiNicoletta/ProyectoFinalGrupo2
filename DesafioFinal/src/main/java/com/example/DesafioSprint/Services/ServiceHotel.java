package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadHotelDTO;
import com.example.DesafioSprint.DTOs.HotelDTO;
import com.example.DesafioSprint.DTOs.HotelResponseDTO;
import com.example.DesafioSprint.DTOs.ListHotelesDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Entities.Hotel;
import com.example.DesafioSprint.Exceptions.VuelosException;
import com.example.DesafioSprint.Repository.IHotelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceHotel implements IServiceHotel {
    private final IHotelRepository repoHotel;

    public ServiceHotel(IHotelRepository repoHotel) {
        this.repoHotel = repoHotel;
    }

    public HotelResponseDTO addHotel(HotelDTO hotelDTO) throws FechasException, VuelosException {
        if (hotelDTO.getAvailableTo().before(hotelDTO.getAvailableFrom()))
            throw new FechasException("La fecha de salida debe ser mayor a la de ida", HttpStatus.BAD_REQUEST);
        if (repoHotel.existsHotel(hotelDTO.getHotelCode()))
            throw new VuelosException("Ya existe un hotel con ese numero", HttpStatus.BAD_REQUEST);
        Hotel hotel = new Hotel(hotelDTO.getHotelCode(), hotelDTO.getName(), hotelDTO.getPlace(), hotelDTO.getRoomType(), hotelDTO.getPriceByNight(), hotelDTO.getAvailableFrom(), hotelDTO.getAvailableTo(), hotelDTO.isReserved());
        repoHotel.save(hotel);
        HotelResponseDTO res = new HotelResponseDTO("Hotel dado de alta correctamente");
        return res;
    }

    /**
     * @return los hoteles ingresados en el sistema con sus respectivos atributos como por ejemplo,nombre, codigo,lugar,tipo de habitacion,precio por noche
     * fecha disponible entrada, fecha disponible salida y si esta reservado o no.
     */
    public ListHotelesDTO getHoteles() throws HotelesException {
        List<Hotel> aux = repoHotel.findAll();
        List<HotelDTO> auxLst = new ArrayList<>();
        ListHotelesDTO res = new ListHotelesDTO();
        if (aux.isEmpty())
            throw new HotelesException("No hay hoteles en el repositorio", HttpStatus.BAD_REQUEST);
        for (Hotel e : aux) {
            HotelDTO nuevo = e.hotelToDTO();
            auxLst.add(nuevo);
        }
        res.setHoteles(auxLst);
        return res;
    }

    public HotelResponseDTO updateHotel(String cod, HotelDTO hotelDTO) throws HotelesException {
        if (!repoHotel.existsHotel(cod))
            throw new HotelesException("No existe ese hotel en el repositorio", HttpStatus.BAD_REQUEST);
        Hotel hotel = repoHotel.findHoteltByCod(cod);
        hotel.setName(hotelDTO.getName());
        hotel.setPlace(hotelDTO.getPlace());
        hotel.setRoomType(hotelDTO.getRoomType());
        hotel.setAvailableFrom(hotelDTO.getAvailableFrom());
        hotel.setAvailableTo(hotelDTO.getAvailableTo());
        hotel.setReserved(hotelDTO.isReserved());
        hotel.setPlace(hotelDTO.getPlace());
        repoHotel.save(hotel);
        HotelResponseDTO res = new HotelResponseDTO("Hotel modificado correctamente");
        return res;
    }

    /**
     * @param hotel En este parametro se recibe los datos para la consulta, ya sea las fechas de la estadia y el destino que se esta buscando
     * @return Devuelve la lista de los hoteles que cumplen los requisitos solicitados.
     * @throws HotelesException Excepcion causada por si no existen hoteles en el destino ingresado
     * @throws FechasException  Excepcion causada por si la fecha de Salida es mayor a la fecha de entrada
     */
    public ListHotelesDTO getHotelesDisponibles(DisponibilidadHotelDTO hotel) throws HotelesException, FechasException {
        ListHotelesDTO res = new ListHotelesDTO();
        if (!repoHotel.existsDestinationHotel(hotel.getDestination()))
            throw new HotelesException("El destino elegido no existe", HttpStatus.BAD_REQUEST);
        if (hotel.getDateTo().before(hotel.getDateFrom()))
            throw new FechasException("La fecha de salida debe ser mayor a la de entrada", HttpStatus.BAD_REQUEST);

        List<Hotel> aux = repoHotel.findAll();
        List<HotelDTO> auxLst = new ArrayList<>();
        for (Hotel e : aux) {
            if ((e.getPlace().equals(hotel.getDestination())) && (!e.isReserved()) && (e.getAvailableFrom().before(hotel.getDateFrom())) && (e.getAvailableTo().after(hotel.getDateTo()))) {
                HotelDTO nuevo = e.hotelToDTO();
                auxLst.add(nuevo);
            }
        }
        res.setHoteles(auxLst);
        if (res.getHoteles().isEmpty())
            throw new HotelesException("No hay hoteles disponibles con los datos ingresados", HttpStatus.BAD_REQUEST);
        return res;
    }

    public HotelResponseDTO deleteHotel(String cod)throws HotelesException {
        Hotel hotel = repoHotel.findHoteltByCod(cod);
        if (hotel == null)
            throw new HotelesException("No existe hotel con ese codigo", HttpStatus.BAD_REQUEST);
        repoHotel.delete(hotel);
        HotelResponseDTO res = new HotelResponseDTO("Hotel borrado correctamente");
        return res;
    }

}
