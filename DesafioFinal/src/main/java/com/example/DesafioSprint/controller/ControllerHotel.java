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
    IServiceHotel hotelService;
    @Autowired
    IServiceBooking hotelBooking;

    /**
     * @param HotelDTO contiene los datos necesarios para la reserva:
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


    // Altas
    @PostMapping("/hotels/new")
    public ResponseEntity<HotelResponseDTO> addHotel(@RequestBody HotelDTO hotelDTO){
        return new ResponseEntity<HotelResponseDTO>(hotelService.addHotel(hotelDTO), HttpStatus.OK);
    }

    @PostMapping("/bookings/new")
    public ResponseEntity<BookingResponseDTO> addReserva(@Valid @RequestBody BookingRequestDTO bookingRequestDTO) throws PersonasException, HotelesException, FechasException, UbicacionException {
        return new ResponseEntity<>(hotelBooking.addReserva(bookingRequestDTO), HttpStatus.OK);
    }

    // Modificaciones

    @PutMapping("/hotels/edit/?hotelCode=code")
    public ResponseEntity<HotelResponseDTO> modifyHotel(@RequestBody HotelDTO hotelDTO){
        return new ResponseEntity<HotelResponseDTO>(hotelService.updateHotel(hotelDTO), HttpStatus.OK);
    }

    @PutMapping("/hotel-bookings/edit/?id=num_id")
    public ResponseEntity<BookingResponseDTO> modifyHotel(@RequestBody BookingRequestDTO bookingRequestDTO){
        return new ResponseEntity<HotelResponseDTO>(hotelBooking.updateBooking(bookingRequestDTO), HttpStatus.OK);
    }


    // Bajas
    @DeleteMapping("/hotels/edit/?hotelCode=code")
    public ResponseEntity<HotelResponseDTO> modifyHotel(@RequestBody HotelDTO hotelDTO){
        return new ResponseEntity<HotelResponseDTO>(hotelService.deleteHotel(hotelDTO), HttpStatus.OK);
    }

    @PutMapping("/hotel-bookings/edit/?id=num_id")
    public ResponseEntity<BookingResponseDTO> modifyHotel(@RequestBody BookingRequestDTO bookingRequestDTO){
        return new ResponseEntity<HotelResponseDTO>(hotelBooking.deleteBooking(bookingRequestDTO), HttpStatus.OK);
    }


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
            return new ResponseEntity<>(hotelService.getHoteles(), HttpStatus.OK);
        else if (dateFrom.isEmpty() || dateTo.isEmpty())
            throw new FaltanParametros("Faltan Parametros para realizar la consulta", HttpStatus.BAD_REQUEST);
        else {
            try {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom);
                Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dateTo);
                nuevo = new DisponibilidadHotelDTO(date1, date2, destination);} catch (Exception e) {
            }
            return new ResponseEntity<>(hotelService.getHotelesDisponibles(nuevo), HttpStatus.OK);
        }
    }


    /**
     * @param cod Recibe el identificador del hotel que quiere consultar
     * @return Todos los datos de las reservas que corresponden al hotel ingresado.
     */
//    @GetMapping("/bookings-history/{cod}")
//    ResponseEntity<List<BookingResponseDTO>> getReservasHoteles(@PathVariable String cod) throws HotelesException {
//        return new ResponseEntity<>(hotelBooking.getReservasHotel(cod), HttpStatus.OK);
//    }

    @GetMapping("/hotel-bookings")
    ResponseEntity<List<BookingDTO>> getReservasHoteles() throws HotelesException {
        return new ResponseEntity<List<BookingDTO>>(hotelBooking.getReservasHotel(), HttpStatus.OK);
    }

}
