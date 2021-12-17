package com.example.DesafioSprint.DTOs;


import com.example.DesafioSprint.Entities.ReservaHotel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class HotelDTO {
    private String hotelCode;
    private String name;
    private String place;
    private String roomType;
    private int priceByNight;
    private Date availableFrom;
    private Date availableTo;
    private boolean reserved;
    List<ReservaHotel> bookings;
}
