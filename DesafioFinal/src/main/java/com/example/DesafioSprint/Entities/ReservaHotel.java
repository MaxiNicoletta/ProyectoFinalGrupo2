package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.BookingDTO;
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
public class ReservaHotel extends Reserva {
    private String hotelCode;
    private int peopleAmount;
    private String roomType;

    public ReservaHotel(String userName, Date dateFrom, Date dateTo, String destination, List<Persona> people, Pago paymentMethod, double amount, double interest, double total, String hotelCode, int peopleAmount, String roomType) {
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

}