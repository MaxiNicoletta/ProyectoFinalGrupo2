package com.example.DesafioSprint.controller;


import com.example.DesafioSprint.DTOs.*;
import com.example.DesafioSprint.Exceptions.*;
import com.example.DesafioSprint.Services.IServiceReservaV;
import com.example.DesafioSprint.Services.IServiceVuelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
public class ControllerFlight {

    @Autowired
    IServiceVuelo service;

    /**
     * Adds a new Hotel to the database.
     * @param flight Flight object.
     * @return FlightResponseDTO
     * @throws PersonasException This exception is thrown when the amount of people doesn't match with the input.
     * @throws FechasException
     * @throws VuelosException
     * @throws UbicacionException
     */
    @PostMapping("/new")
    public FlightResponseDTO newFlight(@RequestBody VueloDTO flight){
        service.addFlight(flight);
        FlightResponseDTO response = new FlightResponseDTO();
        response.setMessage("Vuelo dado de alta correctamente.");
        return response;
    }

    /**
     * Edits the flight number.
     * @param flightNumber New flight number.
     * @return FlightResponseDTO.
     */
    @PutMapping("/edit")
    public FlightResponseDTO editFlightNUmber(@RequestParam String flightNumber){
        service.modifyFlight(flightNumber);
        FlightResponseDTO response = new FlightResponseDTO();
        response.setMessage("Vuelo modificado correctamente.");
        return response;
    }

    /**
     * @param dateFrom    Fecha de origen para listar los vuelos.
     * @param dateTo      Fecha de destino para listar los vuelos.
     * @param origin      Ubicacion origen del vuelo que va a realizar la busqueda.
     * @param destination Ubicacion destino del vuelo que va a realizar la busqueda.
     * @return los posibles vuelos que cumplen con todos los datos ingresados en la consulta.
     * @throws UbicacionException Excepcion causada por si no existen hoteles en el destino ingresado.
     * @throws FechasException    Excepcion causada por si la fecha de Salida es mayor a la fecha de entrada.
     * @throws VuelosException    Excepcion causada por si en el sistema no se registran vuelos de acuerdo a los parametros ingresados anteriormente.
     * @throws FaltanParametros   Excepcion causada por si faltan algunos parametrso en cuanto a la fecha de origen o la fecha de salida.
     */

    @GetMapping("/")
    public ResponseEntity<List<VueloDTO>> listarVuelos(@RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo, @RequestParam(required = false) String origin, @RequestParam(required = false) String destination) throws UbicacionException, FechasException, VuelosException, FaltanParametros {
        DisponibilidadVuelosDTO nuevo = null;
        if (dateFrom == null && dateTo == null && destination == null && origin == null)
            return new ResponseEntity<>(sFlight.getVuelos(), HttpStatus.OK);
        else if (dateFrom.isEmpty() || dateTo.isEmpty())
            throw new FaltanParametros("Faltan Parametros para realizar la consulta", HttpStatus.BAD_REQUEST);
        else {
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom);
                Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateTo);
                nuevo = new DisponibilidadVuelosDTO(date1, date2, origin, destination);} catch (Exception e) {
            }
            return new ResponseEntity<>(sFlight.disponibilidadVuelos(nuevo), HttpStatus.OK);
        }
    }

    /**
     * @param cod Recibe el identificador del vuelo que quiere consultar
     * @return Todos los datos de las reservas que corresponden al vuelo ingresado.
     */
    @GetMapping("/flight-history/{cod}")
    ResponseEntity<List<ReservaVueloResponseDTO>> getReservasVuelos(@PathVariable String cod) throws VuelosException {
        return new ResponseEntity<>(sFlight.getReservasVuelo(cod), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public FlightResponseDTO deleteFlight(@RequestParam String flightNumber){
        service.deleteFlight(flightNumber);
        FlightResponseDTO response = new FlightResponseDTO();
        response.setMessage("Vuelo eliminado correctamente.");
        return response;
    }

}
