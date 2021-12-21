package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.BookingsOrReservationsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingsOrReservations {
    private int firstId;
    private int secondId;

    public BookingsOrReservationsDTO entityToDTO(){
        return new BookingsOrReservationsDTO(getFirstId(),getSecondId());
    }

    public BookingsOrReservations DTOtoEntity(BookingsOrReservationsDTO bookingsOrReservationsDTO){
        return new BookingsOrReservations(bookingsOrReservationsDTO.getFirstId(), bookingsOrReservationsDTO.getSecondId());
    }

}
