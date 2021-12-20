package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadVuelosDTO;
import com.example.DesafioSprint.DTOs.FlightResponseDTO;
import com.example.DesafioSprint.DTOs.VueloDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Exceptions.VuelosException;
import com.example.DesafioSprint.Entities.Vuelo;
import com.example.DesafioSprint.Repository.FlightRepository;
import com.example.DesafioSprint.Repository.IFlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceVuelo implements IServiceVuelo {

    private final IFlightRepository repoFlight;

    public ServiceVuelo(IFlightRepository repoFlight) {
        this.repoFlight = repoFlight;
    }

    public FlightResponseDTO addFlight(VueloDTO flightDTO)throws FechasException,VuelosException{
        if (flightDTO.getReturnDate().after(flightDTO.getGoingDate()))
            throw new FechasException("La fecha de vuelta debe ser mayor a la de ida", HttpStatus.BAD_REQUEST);
        if(repoFlight.existsVuelo(flightDTO.getFlightNumber()))
            throw new VuelosException("Ya existe un vuelo con ese numero", HttpStatus.BAD_REQUEST);
        Vuelo flight = new Vuelo(flightDTO.getFlightNumber(),flightDTO.getName(),flightDTO.getOrigin(),flightDTO.getDestination(),flightDTO.getGoingDate(),flightDTO.getReturnDate(),flightDTO.getSeatType(),flightDTO.getFlightPrice());
        repoFlight.save(flight);
        FlightResponseDTO res = new FlightResponseDTO("Vuelo dado de alta correctamente");
        return res;
    }

    /**
     * @return los vuelos ingresados en el sistema con sus respectivos datos como por ejemplo , numero de vuelo, origen,destino,fecha ida, fecha vuelta,tipo de vuelo, y el precio por persona.
     */
    public List<VueloDTO> getFlights() throws VuelosException {
        List<Vuelo> vuelosData = repoFlight.findAll();
        List<VueloDTO> res = new ArrayList<>();
        if (vuelosData.isEmpty())
            throw new VuelosException("No hay vuelos en el repositorio", HttpStatus.BAD_REQUEST);
        for (Vuelo v : vuelosData) {
            VueloDTO aux = v.flightToDTO();
            res.add(aux);
        }
        return res;
    }
    /**
     * @param vuelo En este parametro se recibe los datos para la consulta, ya sea las fechas de la estadia y el destino que se esta buscando.
     * @return Devuelve la lista de los vuelos que cumplen los requisitos solicitados.
     * @throws UbicacionException Excepcion causada por si no existen hoteles en el destino ingresado.
     * @throws FechasException    Excepcion causada por si la fecha de Salida es mayor a la fecha de entrada.
     * @throws VuelosException    Excepcion causada por si en el sistema no se registran vuelos de acuerdo a los parametros ingresados anteriormente.
     */
    public List<VueloDTO> disponibilidadVuelos(DisponibilidadVuelosDTO vuelo) throws UbicacionException, FechasException, VuelosException {
        List<VueloDTO> res = new ArrayList<>();
        if (!repoFlight.existsDestinationVuelo(vuelo.getDestination()))
            throw new UbicacionException("El destino elegido no existe", HttpStatus.BAD_REQUEST);
        if (!repoFlight.existsOriginVuelo(vuelo.getOrigin()))
            throw new UbicacionException("El origen elegido no existe", HttpStatus.BAD_REQUEST);
        if (vuelo.getDateTo().before(vuelo.getDateFrom()))
            throw new FechasException("La fecha de vuelta debe ser mayor a la de ida", HttpStatus.BAD_REQUEST);
        List<Vuelo> vuelosData = repoFlight.findAll();
        for (Vuelo v : vuelosData) {
            if (v.getOrigin().equals(vuelo.getOrigin()) && v.getDestination().equals(vuelo.getDestination()) && v.getReturnDate().equals(vuelo.getDateTo()) && v.getGoingDate().equals(vuelo.getDateFrom())) {
                VueloDTO nuevo = v.flightToDTO();
                res.add(nuevo);
            }
        }
        if (res.isEmpty())
            throw new VuelosException("No hay Vuelos disponibles con los datos ingresados", HttpStatus.BAD_REQUEST);
        return res;
    }

    public FlightResponseDTO updateFlight(String cod, VueloDTO flightDto) throws  VuelosException{
        if(repoFlight.existsVuelo(flightDto.getFlightNumber()))
        throw new VuelosException("No hay vuelo con ese codigo", HttpStatus.BAD_REQUEST);
        Vuelo flight = repoFlight.findFlightByCod(cod);
        flight.setOrigin(flightDto.getOrigin());
        flight.setDestination(flightDto.getDestination());
        flight.setGoingDate(flightDto.getGoingDate());
        flight.setReturnDate(flightDto.getReturnDate());
        flight.setSeatType(flightDto.getSeatType());
        flight.setPricePerPerson(flightDto.getFlightPrice());
        repoFlight.save(flight);
        FlightResponseDTO res = new FlightResponseDTO("Vuelo modificado correctamente");
        return res;
    }

    public FlightResponseDTO deleteFlight(String cod) throws  VuelosException{
        Vuelo flight = repoFlight.findFlightByCod(cod);
        if(flight==null)
            throw new VuelosException("No hay vuelo con ese codigo", HttpStatus.BAD_REQUEST);
        repoFlight.delete(flight);
        FlightResponseDTO res = new FlightResponseDTO("Vuelo borrado correctamente");
        return res;
    }



}
