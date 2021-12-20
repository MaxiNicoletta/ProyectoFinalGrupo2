package com.example.DesafioSprint.DTOs;

import lombok.Data;

@Data
public class HotelResponseDTO {
    String message;

    public HotelResponseDTO(String message) {
        this.message = "The flight" + message;
    }
}
