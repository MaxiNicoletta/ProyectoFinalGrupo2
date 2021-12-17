package com.example.DesafioSprint.Identity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
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
}
