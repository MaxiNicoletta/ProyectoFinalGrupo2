package com.example.DesafioSprint.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequestDTO {
    private String hotelCode;
    private String name;
    private String place;
    private String roomType;
    private int roomPrice;
    private String disponibilityDateFrom;
    private String disponibilityDateTo;
    private boolean isBooking;
}
