package com.example.DesafioSprint.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date dateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date dateTo;
    private String destination;
    private String hotelCode;
    private int peopleAmount;
    private String roomType;
    private List<@Valid PersonaDTO> people;
}
