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

    @DeleteMapping("/hotels/delete")
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