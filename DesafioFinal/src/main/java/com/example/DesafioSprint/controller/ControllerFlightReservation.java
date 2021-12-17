package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.FlightResponseDTO;
import com.example.DesafioSprint.DTOs.ReservasVueloRequestDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.PersonasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Exceptions.VuelosException;
import com.example.DesafioSprint.Services.IServiceReservaV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/flight-reservation")
public class ControllerFlightReservation {

    @Autowired
    IServiceReservaV bookingService;

    /**
     * @param rsVuelo contiene los datos ncesarios para la reserva:
     *                username
     *                flightReservation que contiene :
     *                dateFrom
     *                dateTo
     *                origin
     *                destination
     *                flightNumber
     *                seats
     *                seatsType
     *                people que contiene:
     *                dni
     *                name
     *                lastname
     *                birthDate
     *                mail
     *                paymentMethod:
     *                type
     *                number
     *                dues
     * @return Devuelve una copia de la reserva que se acaba de realizar mostrando todos los parametros ingresados mas el precio de la reserva.
     * @throws PersonasException  Excepcion cauda por si la cantidad de personas no coincide con la cantidad de datos ingresados.
     * @throws FechasException    Excepcion causada por si la fecha de Salida es mayor a la fecha de entrada.
     * @throws VuelosException    Excepcion causada por si en el sistema no se registran vuelos de acuerdo a los parametros ingresados anteriormente.
     * @throws UbicacionException Excepcion causada por si no existen hoteles en el destino ingresado.
     */
    @PostMapping("/new")
    public FlightResponseDTO addReservaVuelo(@Valid @RequestBody ReservasVueloRequestDTO rsVuelo) throws PersonasException, FechasException, VuelosException, UbicacionException {
        bookingService.addReserva(rsVuelo);
        FlightResponseDTO response = new FlightResponseDTO();
        response.setMessage("Reserva de vuelo dada de alta correctamente.");
        return response;
    }

    /**
     * Edit the flight reservation.
     * @param id Id of the reservation.
     * @return FlightResponseDTO.
     */
    @PutMapping("/edit")
    public FlightResponseDTO editFlightReservation(@RequestParam int id){
        bookingService.modifyFlightReservation(id);
        FlightResponseDTO response = new FlightResponseDTO();
        response.setMessage("Reserva de vuelo modificada correctamente.");
        return response;
    }

    @GetMapping("/")

    @DeleteMapping("/delete")
    public FlightResponseDTO deleteFlightReservation(@RequestParam int id){
        bookingService.deleteFlightReservation(id);
        FlightResponseDTO response = new FlightResponseDTO();
        response.setMessage("Reserva de vuelo eliminada correctamente.");
        return response;
    }
}