package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.BookingsOrReservationsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookingsOrReservations {
    @Id
    private int firstId;
    @Id
    private int secondId;

    public BookingsOrReservationsDTO entityToDTO(){
        return new BookingsOrReservationsDTO(getFirstId(),getSecondId());
    }

    public BookingsOrReservations DTOtoEntity(BookingsOrReservationsDTO bookingsOrReservationsDTO){
        return new BookingsOrReservations(bookingsOrReservationsDTO.getFirstId(), bookingsOrReservationsDTO.getSecondId());
    }

}
