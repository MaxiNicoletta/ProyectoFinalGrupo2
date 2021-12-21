package com.example.DesafioSprint.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonthlyRegisterResponseDTO extends RegisterResponseDTO {
    private Integer month;
    private Integer year;
    private Double total_income;
}
