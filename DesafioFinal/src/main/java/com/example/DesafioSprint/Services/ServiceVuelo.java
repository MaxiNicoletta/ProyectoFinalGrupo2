package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadVuelosDTO;
import com.example.DesafioSprint.DTOs.VueloDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Exceptions.VuelosException;
import com.example.DesafioSprint.Entities.Vuelo;
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

    /**
     * @return los vuelos ingresados en el sistema con sus respectivos datos como por ejemplo , numero de vuelo, origen,destino,fecha ida, fecha vuelta,tipo de vuelo, y el precio por persona.
     */
    public List<VueloDTO> getVuelos() throws VuelosException {
        List<Vuelo> vuelosData = repoFlight.findAll();
        List<VueloDTO> res = new ArrayList<>();
        if (vuelosData.isEmpty())
            throw new VuelosException("No hay vuelos en el repositorio", HttpStatus.BAD_REQUEST);
        for (Vuelo v : vuelosData) {
            VueloDTO aux = new VueloDTO(v.getFlightNumber(), v.getOrigin(), v.getDestination(), v.getDateFrom(), v.getDateTo(), v.getSeatType(), v.getPricePerPerson());
            res.add(aux);
        }
        return res;
    }

    /**
     * @param cod es el codigo que identifica a los vuelos en el sistema
     * @return True si en el sistema existe un vuelo que tenga como codigo identificador = cod y false en el caso contrario
     */
    public boolean existsVuelo(String cod) {
        boolean res = false;
        List<Vuelo> aux = repoFlight.findAll();
        for (Vuelo e : aux) {
            if (e.getFlightNumber().equals(cod)) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * @param dest es el destino donde se va a realizar la busqueda de vuelos
     * @return True si existen vuelo en esa destino y false en el caso contrario.
     */
    public boolean existsDestinationVuelo(String dest) {
        boolean res = false;
        List<Vuelo> aux = repoFlight.findAll();
        for (Vuelo v : aux) {
            if (v.getDestination().equals(dest)) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * @param org es el origen donde se va a realizar la busqueda de vuelos
     * @return True si existen vuelos en esa origen y false en el caso contrario.
     */
    public boolean existsOriginVuelo(String org) {
        boolean res = false;
        List<Vuelo> aux = repoFlight.findAll();
        for (Vuelo v : aux) {
            if (v.getOrigin().equals(org)) {
                res = true;
                break;
            }
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
        if (!existsDestinationVuelo(vuelo.getDestination()))
            throw new UbicacionException("El destino elegido no existe", HttpStatus.BAD_REQUEST);
        if (!existsOriginVuelo(vuelo.getOrigin()))
            throw new UbicacionException("El origen elegido no existe", HttpStatus.BAD_REQUEST);
        if (vuelo.getDateTo().before(vuelo.getDateFrom()))
            throw new FechasException("La fecha de vuelta debe ser mayor a la de ida", HttpStatus.BAD_REQUEST);
        List<Vuelo> vuelosData = repoFlight.findAll();

        for (Vuelo v : vuelosData) {
            if (v.getOrigin().equals(vuelo.getOrigin()) && v.getDestination().equals(vuelo.getDestination()) && v.getDateTo().equals(vuelo.getDateTo()) && v.getDateFrom().equals(vuelo.getDateFrom())) {
                VueloDTO nuevo = new VueloDTO(v.getFlightNumber(), v.getOrigin(), v.getDestination(), v.getDateFrom(), v.getDateTo(), v.getSeatType(), v.getPricePerPerson());
                res.add(nuevo);
            }
        }

        if (res.isEmpty())
            throw new VuelosException("No hay Vuelos disponibles con los datos ingresados", HttpStatus.BAD_REQUEST);
        return res;
    }


}
