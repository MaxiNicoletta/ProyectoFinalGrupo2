//package com.example.DesafioSprint.Entities;
//
//import com.example.DesafioSprint.DTOs.BookingsOrReservationsDTO;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Data
//@NoArgsConstructor
//@Entity
//public class BookingsOrReservations {
//    @Id
//    private int booking_id;
//    private int firstId;
//    private int secondId;
//
//    public BookingsOrReservationsDTO entityToDTO(){
//        return new BookingsOrReservationsDTO(getFirstId(),getSecondId());
//    }
//
//    public BookingsOrReservations(int firstId, int secondId) {
//        this.firstId = firstId;
//        this.secondId = secondId;
//    }
//
//    public BookingsOrReservations DTOtoEntity(BookingsOrReservationsDTO bookingsOrReservationsDTO){
//        return new BookingsOrReservations(bookingsOrReservationsDTO.getFirstId(), bookingsOrReservationsDTO.getSecondId());
//    }
//
//}
