package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadHotelDTO;
import com.example.DesafioSprint.DTOs.HotelDTO;
import com.example.DesafioSprint.DTOs.ListHotelesDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Identity.Hotel;
import com.example.DesafioSprint.Repository.RepositoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceHotel implements IServiceHotel {

    @Autowired
    private RepositoryData repository = new RepositoryData();

    /**
     * @return los hoteles ingresados en el sistema con sus respectivos atributos como por ejemplo,nombre, codigo,lugar,tipo de habitacion,precio por noche
     * fecha disponible entrada, fecha disponible salida y si esta reservado o no.
     */
    public ListHotelesDTO getHoteles() throws HotelesException {
        List<Hotel> aux = repository.getHoteles();
        List<HotelDTO> auxLst = new ArrayList<>();
        ListHotelesDTO res = new ListHotelesDTO();
        if (aux.isEmpty())
            throw new HotelesException("No hay hoteles en el repositorio", HttpStatus.BAD_REQUEST);
        for (Hotel e : aux) {
            HotelDTO nuevo = new HotelDTO(e.getHotelCode(), e.getName(), e.getPlace(), e.getRoomType(), e.getPriceByNight(), e.getAvailableFrom(), e.getAvailableTo(), e.isReserved());
            auxLst.add(nuevo);
        }
        res.setHoteles(auxLst);
        return res;
    }

    /**
     * @param cod es el codigo que identifica a los hoteles en el sistema
     * @return True si en el sistema existe un hotel que tenga como codigo identificador = cod y false en el caso contrario
     */
    public boolean existsHoteles(String cod) {
        boolean res = false;
        List<Hotel> aux = repository.getHoteles();
        for (Hotel e : aux) {
            if (e.getHotelCode().equals(cod)) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * @param dest es el destino donde se va a realizar la busqueda de hoteles
     * @return True si existen hoteles en esa destino y false en el caso contrario.
     */
    public boolean existsDestination(String dest) {
        boolean res = false;
        List<Hotel> aux = repository.getHoteles();
        for (Hotel e : aux) {
            if (e.getPlace().equals(dest)) {
                res = true;
                break;
            }
        }
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
        if (!existsDestination(hotel.getDestination()))
            throw new HotelesException("El destino elegido no existe", HttpStatus.BAD_REQUEST);
        if (hotel.getDateTo().before(hotel.getDateFrom()))
            throw new FechasException("La fecha de salida debe ser mayor a la de entrada", HttpStatus.BAD_REQUEST);

        List<Hotel> aux = repository.getHoteles();
        List<HotelDTO> auxLst = new ArrayList<>();
        for (Hotel e : aux) {
            if ((e.getPlace().equals(hotel.getDestination())) && (!e.isReserved()) && (e.getAvailableFrom().before(hotel.getDateFrom())) && (e.getAvailableTo().after(hotel.getDateTo()))) {
                HotelDTO nuevo = new HotelDTO(e.getHotelCode(), e.getName(), e.getPlace(), e.getRoomType(), e.getPriceByNight(), e.getAvailableFrom(), e.getAvailableTo(), e.isReserved());
                auxLst.add(nuevo);
            }
        }
        res.setHoteles(auxLst);
        if (res.getHoteles().isEmpty())
            throw new HotelesException("No hay hoteles disponibles con los datos ingresados", HttpStatus.BAD_REQUEST);
        return res;
    }

}
