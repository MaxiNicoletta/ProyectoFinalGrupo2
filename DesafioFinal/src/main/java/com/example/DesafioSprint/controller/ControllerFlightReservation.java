package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.FlightResponseDTO;
import com.example.DesafioSprint.DTOs.ReservaVueloDTO;
import com.example.DesafioSprint.DTOs.ReservasVueloRequestDTO;
import com.example.DesafioSprint.DTOs.VueloDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.PersonasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Exceptions.VuelosException;
import com.example.DesafioSprint.Services.IServiceReservaV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<FlightResponseDTO> addReservaVuelo(@Valid @RequestBody ReservasVueloRequestDTO rsVuelo) throws PersonasException, FechasException, VuelosException, UbicacionException {
        return new ResponseEntity<>(bookingService.addReserva(rsVuelo), HttpStatus.OK);
    }


    /**
     * Edit the flight reservation.
     * @param id Id of the reservation.
     * @return FlightResponseDTO.
     */
    @PutMapping("/edit")
    public FlightResponseDTO updateFlightReservation(@RequestParam Long id, @Valid @RequestBody ReservasVueloRequestDTO reservasVueloRequestDTO){
        return bookingService.updateFlightReservation(id, reservasVueloRequestDTO);
    }

    @GetMapping("/")
    public ArrayList<ReservaVueloDTO> getAllReservations() {
        return bookingService.getAllReservations();
    }

    @DeleteMapping("/delete")
    public FlightResponseDTO deleteFlightReservation(@RequestParam Long id){
        return bookingService.deleteFlightReservation(id);
    }
}
