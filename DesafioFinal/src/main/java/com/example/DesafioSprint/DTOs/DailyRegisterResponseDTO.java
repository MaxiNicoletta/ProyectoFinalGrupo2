package com.example.DesafioSprint.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class DailyRegisterResponseDTO extends RegisterResponseDTO{
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date date;
    private Double total_income;
}
