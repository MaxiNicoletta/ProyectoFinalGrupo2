package com.example.DesafioSprint.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class DailyRegisterResponseDTO {
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date date;
    private double total_income;
}
