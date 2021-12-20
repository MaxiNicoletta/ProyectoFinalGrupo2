package com.example.DesafioSprint.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonthlyRegisterResponseDTO {
    private int month;
    private int year;
    private Double total_income;
}
