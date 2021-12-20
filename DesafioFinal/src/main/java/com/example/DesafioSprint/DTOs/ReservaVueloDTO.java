package com.example.DesafioSprint.DTOs;

import com.example.DesafioSprint.Entities.Pago;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaVueloDTO {
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date goingDate;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date returnDate;
    private String origin;
    private String destination;
    private String flightNumber;
    @Min(value = 1, message = "El valor minimo es 1")
    private int seats;
    private String seatType;
    private List<@Valid PersonaDTO> people;
    private Pago parmentMethod;

}
