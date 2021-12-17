package com.example.DesafioSprint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
    private String username;
    private @Valid ReservaDTO booking;
    private PagoDTO paymentMethod;
}
