package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.*;
import com.example.DesafioSprint.Entities.Hotel;
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
public class HotelController {
    @Autowired
    IServiceHotel hotelService;

//    /**
//     * @param HotelDTO contiene los datos necesarios para la reserva:
//     *                 username
//     *                 booking que contiene :
//     *                 dateFrom
//     *                 dateTo
//     *                 destination
//     *                 hotelCode
//     *                 peopleAmount
//     *                 roomType
//     *                 people que contiene:
//     *                 dni
//     *                 name
//     *                 lastname
//     *                 birthDate
//     *                 mail
//     *                 paymentMethod:
//     *                 type
//     *                 number
//     *                 dues
//     * @return Devuelve una copia de la reserva que se acaba de realizar mostrando todos los parametros ingresados mas el precio de la reserva.
//     * @throws PersonasException Excepcion cauda por si la cantidad de personas no coincide con la cantidad de datos ingresados
//     * @throws HotelesException  Excepcion causada por si no existen hoteles en el destino ingresado
//     * @throws FechasException   Excepcion causada por si la fecha de Salida es mayor a la fecha de entrada
//     */


    @PostMapping("/hotels/new")
    public ResponseEntity<HotelResponseDTO> addHotel(@RequestBody HotelRequestDTO hotelDTO) throws VuelosException,FechasException{
        HotelDTO nuevo = null;
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(hotelDTO.getDisponibilityDateFrom());
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(hotelDTO.getDisponibilityDateTo());
            nuevo = new HotelDTO(hotelDTO.getHotelCode(),hotelDTO.getName(),hotelDTO.getPlace(),hotelDTO.getRoomType(),hotelDTO.getRoomPrice(),date1,date2,hotelDTO.isBooking());} catch (Exception e) {
        }
        return new ResponseEntity<>(hotelService.addHotel(nuevo), HttpStatus.OK);
    }

    @PutMapping("/hotels/edit")
    public ResponseEntity<HotelResponseDTO> updateHotel(@RequestParam String hotelCode, @RequestBody HotelRequestDTO hotelDTO) throws HotelesException{
        HotelDTO nuevo = null;
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(hotelDTO.getDisponibilityDateFrom());
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(hotelDTO.getDisponibilityDateTo());
            nuevo = new HotelDTO(hotelDTO.getHotelCode(),hotelDTO.getName(),hotelDTO.getPlace(),hotelDTO.getRoomType(),hotelDTO.getRoomPrice(),date1,date2,hotelDTO.isBooking());} catch (Exception e) {
        }
        return new ResponseEntity<>(hotelService.updateHotel(hotelCode,nuevo), HttpStatus.OK);
    }

    @DeleteMapping("/hotels/edit")
    public ResponseEntity<HotelResponseDTO> deleteHotel(@RequestParam String hotelCode) throws HotelesException{
        return new ResponseEntity<HotelResponseDTO>(hotelService.deleteHotel(hotelCode), HttpStatus.OK);
    }


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


}
//
//    /**
//     * @param dateFrom    Fecha de origen para listar los hoteles
//     * @param dateTo      Fecha de destino para listar los hoteles
//     * @param destination Ubicacion del hotel que va a realizar la busqueda
//     * @return Se devuelve una lista con los hoteles disponibles que coinciden con los parametros ingresados anteriormente tales como las fechas y el destino.
//     * @throws FaltanParametros Excepcion causada por si faltan algunos parametrso en cuanto a la fecha de origen o la fecha de salida
//     * @throws HotelesException Excepcion causada por si no existen hoteles en el destino ingresado
//     * @throws FechasException  Excepcion causada por si la fecha de Salida es mayor a la fecha de entrada
//     */
