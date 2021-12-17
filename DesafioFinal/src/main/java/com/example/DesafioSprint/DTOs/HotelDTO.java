package com.example.DesafioSprint.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

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

}
