package com.example.DesafioSprint.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservasVueloRequestDTO {
    @Email(message = "Por favor ingrese un e-mail válido")
    private String userName;
    private @Valid ReservaVueloDTO flightReservation;
    private PagoDTO paymentMethod;
}
