package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Identity.Hotel;
import com.example.DesafioSprint.Identity.ReservaHotel;
import com.example.DesafioSprint.Identity.ReservaVuelo;
import com.example.DesafioSprint.Identity.Vuelo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@Repository
public class RepositoryData implements IRepositoryData {
    private List<Hotel> hoteles;
    private List<Vuelo> vuelos;
    private Map<String, List<ReservaHotel>> reservasHot;
    private Map<String, List<ReservaVuelo>> reservasVuelo;

    public RepositoryData() {
        vuelos = new ArrayList<>();
        reservasHot = new HashMap<>();
        reservasVuelo = new HashMap<>();
        hoteles = new ArrayList<>();
        hoteles = loadHoteles();
        vuelos = loadVuelos();
    }

    /**
     * @param rsv En este parametro se recibe la reserva para ser ingresada en el sistema la misma cuenta con el UserName de la persona
     *            fecha de origen, fecha salida, destino, Lista de Personas incluidas en el sistema , la forma de pago de la misma y ademas los datos
     *            especifico como el codigo de hotel, el tipo de habitacion y la cantidad de Personas.
     */

    public void addReservaHotel(ReservaHotel rsv) {
        if (reservasHot.get(rsv.getHotelCode()) == null) {
            List<ReservaHotel> aux = new ArrayList<>();
            aux.add(rsv);
            reservasHot.put(rsv.getHotelCode(), aux);
        } else {
            reservasHot.get((rsv.getHotelCode())).add(rsv);
        }
        String hotel = rsv.getHotelCode();
        for (Hotel h : hoteles)
            if (h.getHotelCode().equals(hotel)) {
                h.setAvailableFrom(rsv.getDateTo());
                if (h.getAvailableTo().equals(rsv.getDateTo()))
                    h.setReserved(true);
                break;
            }
    }

    /**
     * @return Este metodo toma de un Json los hoteles que van a estar en el sistema.
     */

    public List<Hotel> loadHoteles() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:hotels.json");
        } catch (FileNotFoundException e) {e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Hotel>> typeref = new TypeReference<>() {
        };
        List<Hotel> hoteles = null;
        try {
            hoteles = objectMapper.readValue(file, typeref);} catch (IOException e) {System.out.println(e.getMessage());e.printStackTrace();
        }
        return hoteles;
    }

    /**
     * @return Este metodo toma de un Json los vuelos que van a estar en el sistema.
     */

    public List<Vuelo> loadVuelos() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:flights.json");
        } catch (FileNotFoundException e) {e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Vuelo>> typeref = new TypeReference<>() {
        };
        List<Vuelo> vuelos = null;
        try {
            vuelos = objectMapper.readValue(file, typeref);
        } catch (IOException e) {e.printStackTrace();
        }
        return vuelos;
    }

    /**
     * @param rsv En este parametro se recibe la reserva para ser ingresada en el sistema la misma cuenta con el UserName de la persona
     *            *            fecha de origen, fecha salida, destino, origen, Lista de las Personas, la forma de pago de la misma y ademas los datos
     *            *            especifico como el numero de vuelo, categoria y la cantidad de Personas.
     */

    public void addReservaVuelo(ReservaVuelo rsv) {
        if (reservasVuelo.get(rsv.getFlightNumber()) == null) {
            List<ReservaVuelo> aux = new ArrayList<>();
            aux.add(rsv);
            reservasVuelo.put(rsv.getFlightNumber(), aux);
        } else {
            reservasVuelo.get((rsv.getFlightNumber())).add(rsv);
        }
    }
}


