package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.*;
import com.example.DesafioSprint.Exceptions.*;
import com.example.DesafioSprint.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ControllerHotel {
    @Autowired
    IServiceHotel sh;
    @Autowired
    IServiceReservaH sR;

    /**
     * @param rsvHotel contiene los datos ncesarios para la reserva:
     *                 username
     *                 booking que contiene :
     *                 dateFrom
     *                 dateTo
     *                 destination
     *                 hotelCode
     *                 peopleAmount
     *                 roomType
     *                 people que contiene:
     *                 dni
     *                 name
     *                 lastname
     *                 birthDate
     *                 mail
     *                 paymentMethod:
     *                 type
     *                 number
     *                 dues
     * @return Devuelve una copia de la reserva que se acaba de realizar mostrando todos los parametros ingresados mas el precio de la reserva.
     * @throws PersonasException Excepcion cauda por si la cantidad de personas no coincide con la cantidad de datos ingresados
     * @throws HotelesException  Excepcion causada por si no existen hoteles en el destino ingresado
     * @throws FechasException   Excepcion causada por si la fecha de Salida es mayor a la fecha de entrada
     */

    @PostMapping("/bookings/new")
    ResponseEntity<ReservasHotelDTOResponse> addReserva(@Valid @RequestBody ReservaHotelRequestDTO rsvHotel) throws PersonasException, HotelesException, FechasException {
        return new ResponseEntity<>(sR.addReserva(rsvHotel), HttpStatus.OK);
    }

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

    @PostMapping("/flight-reservation/new")
    ResponseEntity<ReservaVueloResponseDTO> addReservaVuelo(@Valid @RequestBody ReservasVueloRequestDTO rsVuelo) throws PersonasException, FechasException, VuelosException, UbicacionException {
        return new ResponseEntity<>(sVuelo.addReserva(rsVuelo), HttpStatus.OK);


    /**
     * @param dateFrom    Fecha de origen para listar los hoteles
     * @param dateTo      Fecha de destino para listar los hoteles
     * @param destination Ubicacion del hotel que va a realizar la busqueda
     * @return Se devuelve una lista con los hoteles disponibles que coinciden con los parametros ingresados anteriormente tales como las fechas y el destino.
     * @throws FaltanParametros Excepcion causada por si faltan algunos parametrso en cuanto a la fecha de origen o la fecha de salida
     * @throws HotelesException Excepcion causada por si no existen hoteles en el destino ingresado
     * @throws FechasException  Excepcion causada por si la fecha de Salida es mayor a la fecha de entrada
     */
    @GetMapping("/hotels")
    ResponseEntity<ListHotelesDTO> listarHoteles(@RequestParam(name = "dateFrom", required = false) String dateFrom, @RequestParam(name = "dateTo", required = false) String dateTo, @RequestParam(name = "destination", required = false) String destination) throws FaltanParametros, HotelesException, FechasException {
        DisponibilidadHotelDTO nuevo = null;
        if (dateFrom == null && dateTo == null && destination == null)
            return new ResponseEntity<>(sh.getHoteles(), HttpStatus.OK);
        else if (dateFrom.isEmpty() || dateTo.isEmpty())
            throw new FaltanParametros("Faltan Parametros para realizar la consulta", HttpStatus.BAD_REQUEST);
        else {
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom);
                Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateTo);
                nuevo = new DisponibilidadHotelDTO(date1, date2, destination);} catch (Exception e) {
            }
            return new ResponseEntity<>(sh.getHotelesDisponibles(nuevo), HttpStatus.OK);
        }
    }

    /**
     * @param cod Recibe el identificador del hotel que quiere consultar
     * @return Todos los datos de las reservas que corresponden al hotel ingresado.
     */

    @GetMapping("/bookings-history/{cod}")
    ResponseEntity<List<ReservasHotelDTOResponse>> getReservasHoteles(@PathVariable String cod) throws HotelesException {
        return new ResponseEntity<>(sR.getReservasHotel(cod), HttpStatus.OK);
    }



}
