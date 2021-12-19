package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.HotelDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    private String hotelCode;
    private String name;
    private String place;
    private String roomType;
    private int priceByNight;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date availableFrom;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date availableTo;
    private boolean reserved;

    public Hotel(String hotelCode, String name, String place, String roomType, int priceByNight, Date availableFrom, Date availableTo, boolean reserved) {
        this.hotelCode = hotelCode;
        this.name = name;
        this.place = place;
        this.roomType = roomType;
        this.priceByNight = priceByNight;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.reserved = reserved;
    }
    public HotelDTO hotelToDTO(){
        return new HotelDTO(getHotelCode(),getName(),getPlace(),getRoomType(),getPriceByNight(),getAvailableFrom(),getAvailableTo(),isReserved(),getBookings());
    }
    public Hotel hotelDTOtoHotel(HotelDTO hotelDTO){
        return new Hotel(hotelDTO.getHotelCode(),hotelDTO.getName(),hotelDTO.getPlace(),hotelDTO.getRoomType(),hotelDTO.getPriceByNight(),hotelDTO.getAvailableFrom(),hotelDTO.getAvailableTo(),hotelDTO.isReserved(),hotelDTO.getBookings());
    }
}
