package com.example.DesafioSprint.DTOs;

import lombok.Data;

@Data
public class TouristicPackageResponseDTO {
    String message;

    public TouristicPackageResponseDTO(String message) {
        this.message = message;
    }
}
