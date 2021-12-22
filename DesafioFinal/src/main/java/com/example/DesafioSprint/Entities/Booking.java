package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.BookingDTO;
import com.example.DesafioSprint.DTOs.BookingRequestDTO;
import com.example.DesafioSprint.DTOs.PersonaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("H")
public class Booking extends Reserva {
    private String hotelCode;
    private int peopleAmount;
    private String roomType;

    public Booking(Long id,String userName, Date dateFrom, Date dateTo, String destination, List<Persona> people, Pago paymentMethod, double amount, double interest, double total, String hotelCode, int peopleAmount, String roomType) {
        super(id,userName, dateFrom, dateTo, destination, people, paymentMethod, amount, interest, total);
        this.hotelCode = hotelCode;
        this.peopleAmount = peopleAmount;
        this.roomType = roomType;
    }

    public Booking(String userName, Date dateFrom, Date dateTo, String destination, List<Persona> people, Pago paymentMethod, double amount, double interest, double total, String hotelCode, int peopleAmount, String roomType) {
        super(userName, dateFrom, dateTo, destination, people, paymentMethod, amount, interest, total);
        this.hotelCode = hotelCode;
        this.peopleAmount = peopleAmount;
        this.roomType = roomType;
    }

    public BookingDTO bookingToDTO() {
        List<PersonaDTO> people = new ArrayList<>();
        for (Persona p : getPeople()) {
            PersonaDTO personaDTO = p.personaToDTO();
            people.add(personaDTO);
        }
        return new BookingDTO(getId(), getDateFrom(), getDateTo(), getDestination(), getHotelCode(), getPeopleAmount(), getRoomType(), people);
    }

    public Booking bookingDTOtoBooking(BookingRequestDTO bookingRequestDTO) {
       Booking booking = new Booking();
       booking.setHotelCode(bookingRequestDTO.getBooking().getHotelCode());
       Persona person = new Persona();
       booking.setPeople(person.PeopleDTOtoPeople(bookingRequestDTO.getBooking().getPeople()));
       booking.setPeopleAmount(bookingRequestDTO.getBooking().getPeopleAmount());
       booking.setRoomType(bookingRequestDTO.getBooking().getRoomType());
       booking.setDateFrom(bookingRequestDTO.getBooking().getDateFrom());
       booking.setDateTo(bookingRequestDTO.getBooking().getDateTo());
       booking.setDestination(bookingRequestDTO.getBooking().getDestination());
       booking.setUserName(bookingRequestDTO.getUsername());
       Pago payment = new Pago();
       payment.paymentDTOtoPayment(bookingRequestDTO.getPaymentMethod());
       booking.setPaymentMethod(payment);
       booking.setId(bookingRequestDTO.getBooking().getId());
       return booking;
    }

}