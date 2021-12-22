package com.example.DesafioSprint.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PackageResponseDTO {
    private String message;

    public PackageResponseDTO(String message) {
        this.message = message;
    }
}
