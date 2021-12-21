package com.example.DesafioSprint.Services;


import com.example.DesafioSprint.DTOs.DailyRegisterResponseDTO;
import com.example.DesafioSprint.DTOs.MonthlyRegisterResponseDTO;

import java.util.Date;

public interface IServiceRegister {

    DailyRegisterResponseDTO getDailyAmount(Date date);

    MonthlyRegisterResponseDTO getMonthlyAmount(Integer month, Integer year);
}
